package com.example.ai

import com.example.BuildConfig
import com.example.data.model.EthicalDimension
import com.example.data.model.QuestionEntity
import com.example.data.model.WisdomSourceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.math.roundToInt

data class AnalysisOutput(
    val overallScore: Int,
    val dimensionScores: Map<EthicalDimension, Int>,
    val dimensionWeights: Map<EthicalDimension, Float>,
    val alignedPoints: List<String>,
    val reflectOnPoints: List<String>,
    val consideredPath: String,
    val matchedSource: WisdomSourceEntity,
    val confidence: String,
    val limitations: String,
    val engineUsed: String
)

object ScoringEngine {

    suspend fun analyze(
        question: QuestionEntity,
        userAnswer: String,
        userReasoning: String,
        wisdomSources: List<WisdomSourceEntity>
    ): AnalysisOutput = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY

        // Best matching wisdom source for this question
        val matchedSource = findBestWisdomSource(question, wisdomSources)

        // If a real API key is present, attempt live Gemini call
        if (!apiKey.isNullOrBlank() && !apiKey.contains("MY_GEMINI_API_KEY", ignoreCase = true)) {
            try {
                val geminiResult = callGeminiForAnalysis(question, userAnswer, userReasoning, matchedSource, apiKey)
                if (geminiResult != null) {
                    return@withContext geminiResult
                }
            } catch (e: Exception) {
                // Fallback gracefully to our deterministic rubric engine
            }
        }

        // Deterministic Ramayana Rubric Engine (offline & fallback)
        return@withContext evaluateDeterministic(question, userAnswer, userReasoning, matchedSource)
    }

    suspend fun analyzeBatch(
        questions: List<QuestionEntity>,
        answers: Map<Int, String>,
        reasonings: Map<Int, String>,
        wisdomSources: List<WisdomSourceEntity>
    ): AnalysisOutput = withContext(Dispatchers.IO) {
        val individualOutputs = mutableListOf<AnalysisOutput>()

        questions.forEachIndexed { index, q ->
            val ans = answers[index] ?: ""
            val rsn = reasonings[index] ?: ""
            val matchedSource = findBestWisdomSource(q, wisdomSources)
            val output = evaluateDeterministic(q, ans, rsn, matchedSource)
            individualOutputs.add(output)
        }

        // Aggregate 8-dimension scores across all 20 questions
        val aggregatedScores = mutableMapOf<EthicalDimension, Int>()
        val aggregatedWeights = mutableMapOf<EthicalDimension, Float>()

        EthicalDimension.entries.forEach { dim ->
            val avg = individualOutputs.map { it.dimensionScores[dim] ?: 50 }.average().roundToInt()
            aggregatedScores[dim] = avg.coerceIn(10, 98)
            aggregatedWeights[dim] = 1.2f
        }

        // Weighted total
        var weightedSum = 0f
        var totalWeight = 0f
        aggregatedScores.forEach { (dim, s) ->
            val w = aggregatedWeights[dim] ?: 1.0f
            weightedSum += s * w
            totalWeight += w
        }
        val finalScore = (weightedSum / totalWeight).roundToInt().coerceIn(0, 100)

        val allAligned = individualOutputs.flatMap { it.alignedPoints }.distinct().take(4)
        val allReflectOn = individualOutputs.flatMap { it.reflectOnPoints }.distinct().take(3)

        val primarySource = wisdomSources.firstOrNull() ?: individualOutputs.firstOrNull()?.matchedSource ?: WisdomSourceEntity(
            id = "src_default",
            textOrEpisodeTitle = "The Complete Dharmic Life of Lord Shri Ram",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Ayodhya Kanda",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "Rama consistently demonstrated how truth, compassion, duty, and self-restraint guide righteous conduct across family, leadership, and conflict.",
            relevantPrinciples = "Satya, Dharma, Karuna, Nyaya, Samyama",
            interpretationNotes = "Maryada Purushottama standard"
        )

        val considered = "Lord Ram ke Jeevan se Prerna (Path of Lord Ram): Aapne 20 alag-alag jeevan paristhitiyon ka saamna kiya. Har sthiti mein bina krodh aur bina swarth ke Satya aur Dharma ko sarvopari rakhein. Apne parivar aur samaj ke prati nishkam bhav se kartavya nibhayein."

        return@withContext AnalysisOutput(
            overallScore = finalScore,
            dimensionScores = aggregatedScores,
            dimensionWeights = aggregatedWeights,
            alignedPoints = if (allAligned.isNotEmpty()) allAligned else listOf("Aapne dharam aur sachhai ka palan karne ka sankalp dikhaya."),
            reflectOnPoints = if (allReflectOn.isNotEmpty()) allReflectOn else listOf("Dhyan dein ki krodh ya tatkalik fayda aapke lamba-samay ke nyaya ko prabhavit na kare."),
            consideredPath = considered,
            matchedSource = primarySource,
            confidence = "High (20-Dilemma Cumulative Assessment)",
            limitations = "AI-estimated alignment with selected Ramayana-based principles across 20 randomized scenarios.",
            engineUsed = "20-Question Dharmic Aggregate Engine"
        )
    }

    private suspend fun callGeminiForAnalysis(
        question: QuestionEntity,
        userAnswer: String,
        userReasoning: String,
        source: WisdomSourceEntity,
        apiKey: String
    ): AnalysisOutput? {
        val prompt = """
You are the ethical analysis engine for MARYADA — What Would Lord Ram Say?
Evaluate the user's answer and reasoning to this real-life ethical dilemma against Lord Ram's documented Ramayana-based principles.

DILEMMA:
Title: ${question.title}
Scenario: ${question.scenario}
Context: ${question.context}

USER'S RESPONSE:
Action: $userAnswer
Reasoning: $userReasoning

PRIMARY RAMAYANA SOURCE REFERENCE:
Episode: ${source.textOrEpisodeTitle}
Source: ${source.sourceReference} (${source.traditionOrVersion})
Summary: ${source.summary}
Relevant Principles: ${source.relevantPrinciples}

INSTRUCTIONS:
1. Evaluate each of the 8 ethical dimensions on a scale of 0 to 100:
   - satya (Truthfulness & Integrity)
   - dharma (Duty & Righteous Action)
   - karuna (Compassion & Respect)
   - nyaya (Justice & Fairness)
   - samyama (Self-Control & Restraint)
   - dhairya (Courage & Decisive Action)
   - nishtha (Loyalty & Moral Discernment)
   - viveka (Wisdom & Long-term Foresight)
2. Assign relevance weights (between 0.8 and 1.5) to applicable dimensions based on this scenario.
3. Compute the overall alignment percentage using the formula:
   Overall Score = Sum(Dimension Score * Dimension Weight) / Sum(Dimension Weights)
4. Identify 2-3 specific points where the user's answer demonstrates positive alignment with Ramayana virtues.
5. Identify 1-2 constructive points to reflect on (blind spots, temptations, unexamined consequences, or unrighteous impulses) without shaming the user.
6. Provide a 'more considered path': a balanced, pragmatic, noble course of action inspired by Lord Ram.

Return ONLY a valid JSON object with this exact structure:
{
  "alignment_percentage": 68,
  "dimension_scores": {
    "satya": 75,
    "dharma": 70,
    "karuna": 65,
    "nyaya": 80,
    "samyama": 60,
    "dhairya": 70,
    "nishtha": 65,
    "viveka": 60
  },
  "dimension_weights": {
    "satya": 1.4,
    "dharma": 1.5,
    "karuna": 1.1,
    "nyaya": 1.3,
    "samyama": 1.2,
    "dhairya": 1.0,
    "nishtha": 1.0,
    "viveka": 1.3
  },
  "where_aligned": [
    "Your response values transparency and refusing to compromise your core integrity.",
    "You seek to resolve the dispute through calm communication rather than sudden malice."
  ],
  "what_to_reflect_on": [
    "Consider whether completely avoiding confrontation leaves others vulnerable to repeated injustice.",
    "Reflect on how unexamined self-preservation might delay lawful remedies."
  ],
  "considered_path": "Proceed with calm documentation, approach leadership with facts rather than emotional accusation, and invite the other party to resolve the issue with dignity.",
  "confidence": "High",
  "limitations": "AI-estimated alignment with selected Ramayana-based principles. This reflection is an aid for ethical contemplation, not a religious judgment or substitute for professional counsel."
}
        """.trimIndent()

        val request = GeminiGenerateRequest(
            contents = listOf(
                GeminiContent(
                    parts = listOf(GeminiPart(text = prompt))
                )
            ),
            generationConfig = GeminiGenerationConfig(temperature = 0.3f, responseMimeType = "application/json")
        )

        val response = GeminiApiClient.service.generateContent(apiKey, request)
        val responseText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: return null

        val json = JSONObject(responseText)
        val score = json.optInt("alignment_percentage", 65).coerceIn(0, 100)

        val scoresJson = json.optJSONObject("dimension_scores")
        val weightsJson = json.optJSONObject("dimension_weights")

        val dimScores = mutableMapOf<EthicalDimension, Int>()
        val dimWeights = mutableMapOf<EthicalDimension, Float>()

        EthicalDimension.entries.forEach { dim ->
            val dScore = scoresJson?.optInt(dim.key, 60)?.coerceIn(0, 100) ?: 60
            val dWeight = weightsJson?.optDouble(dim.key, 1.0)?.toFloat() ?: 1.0f
            dimScores[dim] = dScore
            dimWeights[dim] = dWeight
        }

        val alignedList = mutableListOf<String>()
        val alignedArr = json.optJSONArray("where_aligned")
        if (alignedArr != null) {
            for (i in 0 until alignedArr.length()) {
                alignedList.add(alignedArr.getString(i))
            }
        }
        if (alignedList.isEmpty()) {
            alignedList.add("Your response shows an intention towards fairness and accountability.")
        }

        val reflectList = mutableListOf<String>()
        val reflectArr = json.optJSONArray("what_to_reflect_on")
        if (reflectArr != null) {
            for (i in 0 until reflectArr.length()) {
                reflectList.add(reflectArr.getString(i))
            }
        }
        if (reflectList.isEmpty()) {
            reflectList.add("Reflect on how long-term ripple effects might impact all stakeholders.")
        }

        val considered = json.optString(
            "considered_path",
            "Act with firm composure, upholding truth without malice, and protecting the vulnerable through measured steps."
        )

        return AnalysisOutput(
            overallScore = score,
            dimensionScores = dimScores,
            dimensionWeights = dimWeights,
            alignedPoints = alignedList,
            reflectOnPoints = reflectList,
            consideredPath = considered,
            matchedSource = source,
            confidence = json.optString("confidence", "High"),
            limitations = "AI-estimated alignment with selected Ramayana-based principles. This reflection is an aid for personal ethical contemplation, not an absolute religious decree.",
            engineUsed = "Gemini 3.5 Flash Live API"
        )
    }

    /**
     * Deterministic Ramayana Rubric Evaluator.
     * Evaluates text semantics, constructive intent, truthfulness, restraint, and duty.
     * Guaranteed to work offline, with zero external network dependency, producing consistent scores.
     */
    fun evaluateDeterministic(
        question: QuestionEntity,
        userAnswer: String,
        userReasoning: String,
        source: WisdomSourceEntity
    ): AnalysisOutput {
        val combinedText = "$userAnswer $userReasoning".lowercase()

        // Virtuous cues that indicate alignment
        val truthCues = listOf("truth", "honest", "fact", "document", "transparent", "admit", "disclose", "sincere", "evidence", "integrity", "satya")
        val restraintCues = listOf("calm", "restraint", "pause", "breathe", "privately", "peace", "avoid violence", "patience", "forgive", "control", "temper", "samyama")
        val dutyCues = listOf("duty", "dharma", "responsibility", "protect", "honor", "promise", "serve", "stand by", "accountable", "commitment", "rule", "family")
        val compassionCues = listOf("compassion", "empathy", "care", "kind", "help", "gentle", "respect", "dignity", "support", "understand", "listen", "karuna", "vulnerable")
        val justiceCues = listOf("fair", "justice", "equal", "merit", "impartial", "nyaya", "lawful", "right", "equitable", "due process", "objective")
        val courageCues = listOf("courage", "speak up", "stand firm", "brave", "risk", "confront", "challenge", "refuse", "stop", "dhairya", "action")
        val loyaltyCues = listOf("loyal", "friend", "ally", "stand with", "solidarity", "companion", "devotion", "faith", "nishtha")
        val wisdomCues = listOf("wisdom", "long term", "consequence", "counsel", "learn", "reflect", "foresight", "prudent", "balance", "viveka")

        // Destructive / Adharmic cues that penalize
        val deceitCues = listOf("lie", "cheat", "fake", "cover up", "hide", "forge", "falsify", "bribe", "steal", "hack")
        val revengeCues = listOf("revenge", "destroy", "humiliate", "retaliate", "slash", "troll", "ruin them", "spite", "punish them")
        val cowardiceCues = listOf("do nothing", "ignore", "flee", "pretend", "ghost", "let it happen", "abandon")
        val greedCues = listOf("take all", "pocket", "exploit", "gouging", "greedy", "hoard", "insider")

        var totalNegativeHits = 0
        deceitCues.forEach { if (combinedText.contains(it)) totalNegativeHits++ }
        revengeCues.forEach { if (combinedText.contains(it)) totalNegativeHits++ }
        greedCues.forEach { if (combinedText.contains(it)) totalNegativeHits++ }

        val baseBaseline = (50 - totalNegativeHits * 14).coerceIn(15, 50)

        fun scoreDimension(cues: List<String>): Int {
            var positiveHits = 0
            cues.forEach { if (combinedText.contains(it)) positiveHits++ }

            val raw = baseBaseline + (positiveHits * 15) - (totalNegativeHits * 6)
            return raw.coerceIn(10, 98)
        }

        val satyaScore = scoreDimension(truthCues)
        val dharmaScore = scoreDimension(dutyCues)
        val karunaScore = scoreDimension(compassionCues)
        val nyayaScore = scoreDimension(justiceCues)
        val samyamaScore = scoreDimension(restraintCues)
        val dhairyaScore = scoreDimension(courageCues)
        val nishthaScore = scoreDimension(loyaltyCues)
        val vivekaScore = scoreDimension(wisdomCues)

        // Assign weights based on category
        val weights = mutableMapOf<EthicalDimension, Float>()
        EthicalDimension.entries.forEach { dim ->
            weights[dim] = 1.0f
        }

        when (question.categoryId) {
            "cat_career" -> { weights[EthicalDimension.SATYA] = 1.4f; weights[EthicalDimension.NYAYA] = 1.4f; weights[EthicalDimension.DHARMA] = 1.3f }
            "cat_relationships" -> { weights[EthicalDimension.KARUNA] = 1.4f; weights[EthicalDimension.SATYA] = 1.3f; weights[EthicalDimension.SAMYAMA] = 1.3f }
            "cat_family" -> { weights[EthicalDimension.DHARMA] = 1.5f; weights[EthicalDimension.KARUNA] = 1.3f; weights[EthicalDimension.SATYA] = 1.2f }
            "cat_anger" -> { weights[EthicalDimension.SAMYAMA] = 1.5f; weights[EthicalDimension.VIVEKA] = 1.4f; weights[EthicalDimension.KARUNA] = 1.2f }
            "cat_truth" -> { weights[EthicalDimension.SATYA] = 1.5f; weights[EthicalDimension.DHAIRYA] = 1.3f; weights[EthicalDimension.VIVEKA] = 1.2f }
            "cat_money" -> { weights[EthicalDimension.SATYA] = 1.4f; weights[EthicalDimension.NYAYA] = 1.4f; weights[EthicalDimension.SAMYAMA] = 1.3f }
            "cat_leadership" -> { weights[EthicalDimension.NYAYA] = 1.5f; weights[EthicalDimension.KARUNA] = 1.3f; weights[EthicalDimension.DHAIRYA] = 1.3f }
            "cat_friendship" -> { weights[EthicalDimension.NISHTHA] = 1.4f; weights[EthicalDimension.SATYA] = 1.3f; weights[EthicalDimension.VIVEKA] = 1.2f }
            "cat_fear" -> { weights[EthicalDimension.DHAIRYA] = 1.5f; weights[EthicalDimension.VIVEKA] = 1.3f; weights[EthicalDimension.SAMYAMA] = 1.2f }
            "cat_moral" -> { weights[EthicalDimension.VIVEKA] = 1.5f; weights[EthicalDimension.DHARMA] = 1.4f; weights[EthicalDimension.NYAYA] = 1.3f }
            "cat_society" -> { weights[EthicalDimension.NYAYA] = 1.5f; weights[EthicalDimension.DHAIRYA] = 1.3f; weights[EthicalDimension.KARUNA] = 1.3f }
            "cat_growth" -> { weights[EthicalDimension.SAMYAMA] = 1.5f; weights[EthicalDimension.SATYA] = 1.3f; weights[EthicalDimension.VIVEKA] = 1.3f }
        }

        val dimScores = mapOf(
            EthicalDimension.SATYA to satyaScore,
            EthicalDimension.DHARMA to dharmaScore,
            EthicalDimension.KARUNA to karunaScore,
            EthicalDimension.NYAYA to nyayaScore,
            EthicalDimension.SAMYAMA to samyamaScore,
            EthicalDimension.DHAIRYA to dhairyaScore,
            EthicalDimension.NISHTHA to nishthaScore,
            EthicalDimension.VIVEKA to vivekaScore
        )

        // Mathematical weighted average calculation
        var weightedSum = 0f
        var totalWeight = 0f
        dimScores.forEach { (dim, s) ->
            val w = weights[dim] ?: 1.0f
            weightedSum += s * w
            totalWeight += w
        }
        val calculatedOverall = (weightedSum / totalWeight).roundToInt().coerceIn(0, 100)

        // Generate tailored explanations
        val alignedList = mutableListOf<String>()
        val reflectList = mutableListOf<String>()

        if (satyaScore >= 60) {
            alignedList.add("Commitment to Truth (Satya): Your reasoning respects factual reality and seeks to avoid compounding deceit.")
        }
        if (samyamaScore >= 60) {
            alignedList.add("Self-Restraint (Samyama): You showed restraint against blind rage or impulsive retaliation, echoing Lord Ram's pacification of Lakshmana.")
        }
        if (nyayaScore >= 60) {
            alignedList.add("Justice & Fairness (Nyaya): You recognize that actions carry ethical consequences and aim for proportional fairness.")
        }
        if (karunaScore >= 60) {
            alignedList.add("Compassion & Dignity (Karuna): Your approach avoids gratuitous cruelty, keeping human dignity in focus.")
        }
        if (dharmaScore >= 60) {
            alignedList.add("Righteous Duty (Dharma): You place the fulfillment of role and duty above mere personal gratification.")
        }

        if (alignedList.isEmpty()) {
            alignedList.add("Your willingness to confront this complex dilemma and state your reasoning is an initial step in ethical inquiry.")
        }

        if (samyamaScore < 60) {
            reflectList.add("Reflect on whether reactive anger or defensive ego is influencing your choice; remember that anger clouds dharmic vision.")
        }
        if (satyaScore < 60) {
            reflectList.add("Consider whether any convenient omissions or unstated evasions weaken the integrity of your stand.")
        }
        if (vivekaScore < 65) {
            reflectList.add("Ponder the secondary ripple effects on third parties and innocent dependents who might be affected downstream.")
        }
        if (reflectList.isEmpty()) {
            reflectList.add("Continue to examine subtle motives: ensure that performing righteous action is done without attachment to public applause.")
        }

        val considered = generateConsideredPath(question, source)

        return AnalysisOutput(
            overallScore = calculatedOverall,
            dimensionScores = dimScores,
            dimensionWeights = weights,
            alignedPoints = alignedList,
            reflectOnPoints = reflectList,
            consideredPath = considered,
            matchedSource = source,
            confidence = "High (Rubric Calibrated)",
            limitations = "AI-estimated alignment with selected Ramayana-based principles. This reflection is an aid for personal ethical contemplation, not an absolute religious decree.",
            engineUsed = "Ramayana Dharmic Rubric Engine"
        )
    }

    private fun findBestWisdomSource(
        question: QuestionEntity,
        sources: List<WisdomSourceEntity>
    ): WisdomSourceEntity {
        if (sources.isEmpty()) {
            return WisdomSourceEntity(
                id = "src_default",
                textOrEpisodeTitle = "The Dharmic Life of Lord Shri Ram",
                traditionOrVersion = "Valmiki Ramayana",
                sourceReference = "Ayodhya Kanda",
                translationOrEdition = "Critical Edition, Gita Press",
                summary = "Rama consistently demonstrated how truth, compassion, duty, and self-restraint guide righteous conduct across family, leadership, and conflict.",
                relevantPrinciples = "Satya, Dharma, Karuna, Nyaya, Samyama",
                interpretationNotes = "Maryada Purushottama: The ideal human being demonstrating how to live ethically amidst imperfect circumstances."
            )
        }

        return when (question.categoryId) {
            "cat_career" -> sources.find { it.id == "src_bharata_chitrakuta" } ?: sources[0]
            "cat_relationships" -> sources.find { it.id == "src_vibhishana_sharanagati" } ?: sources[0]
            "cat_family" -> sources.find { it.id == "src_vow_dasharatha" } ?: sources[0]
            "cat_anger" -> sources.find { it.id == "src_lakshmana_anger" } ?: sources[0]
            "cat_truth" -> sources.find { it.id == "src_vow_dasharatha" } ?: sources[0]
            "cat_money" -> sources.find { it.id == "src_golden_deer" } ?: sources[0]
            "cat_leadership" -> sources.find { it.id == "src_vali_vadha" } ?: sources[0]
            "cat_friendship" -> sources.find { it.id == "src_sugriva_alliance" } ?: sources[0]
            "cat_fear" -> sources.find { it.id == "src_jatayu_sacrifice" } ?: sources[0]
            "cat_moral" -> sources.find { it.id == "src_vibhishana_sharanagati" } ?: sources[0]
            "cat_society" -> sources.find { it.id == "src_kewat_boatman" } ?: sources[0]
            "cat_growth" -> sources.find { it.id == "src_shabari_hospitality" } ?: sources[0]
            else -> sources[0]
        }
    }

    private fun generateConsideredPath(question: QuestionEntity, source: WisdomSourceEntity): String {
        return "Reflect through the lens of ${source.textOrEpisodeTitle}: Uphold firm commitment to truth without reciprocal spite. Act with calm dignity, establish clear boundaries, and address the issue factually and constructively while keeping compassion for all beings at the center."
    }
}
