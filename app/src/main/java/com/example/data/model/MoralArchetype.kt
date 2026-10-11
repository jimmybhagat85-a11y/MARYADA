package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.DivyaGoldPrimary
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.WarmGlowOrange

data class MoralArchetype(
    val titleEn: String,
    val titleHi: String,
    val badgeSymbol: String,
    val scoreRange: String,
    val auraName: String,
    val auraColor: Color,
    val accentColor: Color,
    val shortDescription: String,
    val hindiSummary: String,
    val viralQuote: String,
    val ramayanaContext: String
) {
    fun generateShareMessage(score: Int): String {
        return """
🏹 *MARYADA TEST: What Would Lord Ram Say?* 🏹
━━━━━━━━━━━━━━━━━━━━
✨ My Score: *$score%*
👑 Moral Archetype: *$titleEn ($titleHi)*
🌟 Aura: *$auraName*

"$viralQuote"

I just answered 20 real-life moral dilemmas of modern life.
Lord Ram's golden hologram revealed my conscience alignment!

Dekhein kya aap Kalyug mein Maryada ka palan kar sakte hain?
Take the 20-Question Test on MARYADA App!
#MaryadaTest #LordRam #DharmaInModernLife #RamayanaWisdom
        """.trimIndent()
    }

    fun generateChallengeMessage(score: Int): String {
        return """
🏹 *CHALLENGE: Beat My Maryada Score ($score%)!* 🏹

I took the 20-Question Maryada Test and earned the archetype *$titleEn ($titleHi)* with *$score% alignment* to Lord Ram's virtues!

Dekhte hain aap office, family, aur greed ke kalyug dilemmas mein kitne dharmi hain!
Are you ready to test your moral alignment?
#MaryadaChallenge #DharmaTest
        """.trimIndent()
    }
}

object MoralArchetypes {
    val DHARMIC_WARRIOR = MoralArchetype(
        titleEn = "Dharmic Warrior",
        titleHi = "धर्म योद्धा",
        badgeSymbol = "👑",
        scoreRange = "90% – 100%",
        auraName = "Divya Solar Aura (दिव्य सौर आभामंडल)",
        auraColor = DivyaGoldPrimary,
        accentColor = Color(0xFFFFF176),
        shortDescription = "Like Lord Ram at the threshold of Ayodhya: You uphold truth and righteous duty even at supreme personal cost.",
        hindiSummary = "सर्वोच्च मर्यादा — कठिन से कठिन परिस्थिति में भी सत्य और कर्तव्य का मार्ग नहीं छोड़ते।",
        viralQuote = "धर्मो रक्षति रक्षितः • Dharma protects those who protect Dharma.",
        ramayanaContext = "Lord Ram renouncing Ayodhya's crown without hesitation to protect King Dasharatha's word."
    )

    val SATYA_SADHAK = MoralArchetype(
        titleEn = "Satya Sadhak",
        titleHi = "सत्य साधक",
        badgeSymbol = "🏹",
        scoreRange = "75% – 89%",
        auraName = "Golden Radiance Aura (स्वर्ण आभा)",
        auraColor = GoldenAmber,
        accentColor = DivyaGoldPrimary,
        shortDescription = "High moral clarity: You refuse shortcuts, stand by principles under pressure, and value your word.",
        hindiSummary = "सत्यनिष्ठ विवेक — सुविधा से ऊपर मर्यादा और अपने वचन को प्राथमिकता देते हैं।",
        viralQuote = "रघुकुल रीति सदा चलि आई, प्राण जाहुँ बरु बचनु न जाई।",
        ramayanaContext = "Bharat refusing to take the throne, ruling with reverence for Ram's padukas."
    )

    val NYAYA_MITRA = MoralArchetype(
        titleEn = "Nyaya Mitra",
        titleHi = "न्याय मित्र",
        badgeSymbol = "⚖️",
        scoreRange = "60% – 74%",
        auraName = "Amber Balance Aura (संतुलित आभा)",
        auraColor = WarmGlowOrange,
        accentColor = GoldenAmber,
        shortDescription = "Balanced pragmatist: You strive to do right while weighing worldly constraints and relationships.",
        hindiSummary = "संतुलित दृष्टि — न्याय और व्यावहारिक परिस्थितियों के बीच संतुलन बनाते हैं।",
        viralQuote = "सत्यमेव जयते नानृतम् • Truth alone triumphs; wisdom grows with every choice.",
        ramayanaContext = "Vibhishana counseling Ravana with moral truth before choosing righteousness."
    )

    val SAMYAM_YOGI = MoralArchetype(
        titleEn = "Samyam Yogi",
        titleHi = "संयम खोजी",
        badgeSymbol = "🕊️",
        scoreRange = "45% – 59%",
        auraName = "Dawn Violet Aura (साधना आभा)",
        auraColor = Color(0xFFBA68C8),
        accentColor = Color(0xFFCE93D8),
        shortDescription = "Awakening conscience: You wrestle with anger and ego, steadily learning to choose restraint.",
        hindiSummary = "आत्म-संयम की साधना — क्रोध और लालच पर विजय पाने की यात्रा पर अग्रसर।",
        viralQuote = "मन की शांति और इंद्रिय संयम ही सच्ची मर्यादा की नींव है।",
        ramayanaContext = "Lakshmana restraining his rage against Kaikeyi when guided by Ram's divine calm."
    )

    val KALYUG_SEEKER = MoralArchetype(
        titleEn = "Kalyug Seeker",
        titleHi = "सत्य का अभिलाषी",
        badgeSymbol = "🌱",
        scoreRange = "0% – 44%",
        auraName = "Ember Spark Aura (जागृति आभा)",
        auraColor = Color(0xFF81C784),
        accentColor = Color(0xFFA5D6A7),
        shortDescription = "The spark stirs: Worldly noise and self-interest challenge your resolve, yet your inner seeker seeks clarity.",
        hindiSummary = "जागृति का प्रारंभ — सांसारिक आकर्षणों के बीच अंतरात्मा की आवाज़ पहचानना सीख रहे हैं।",
        viralQuote = "तमसो मा ज्योतिर्गमय • Lead me from darkness into the eternal light of Dharma.",
        ramayanaContext = "Sugriva overcoming forgetfulness and returning to duty upon Lakshmana's awakening."
    )

    val ALL = listOf(DHARMIC_WARRIOR, SATYA_SADHAK, NYAYA_MITRA, SAMYAM_YOGI, KALYUG_SEEKER)

    fun forScore(score: Int): MoralArchetype = when {
        score >= 90 -> DHARMIC_WARRIOR
        score >= 75 -> SATYA_SADHAK
        score >= 60 -> NYAYA_MITRA
        score >= 45 -> SAMYAM_YOGI
        else -> KALYUG_SEEKER
    }
}
