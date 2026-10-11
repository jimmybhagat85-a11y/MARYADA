package com.example.data.db

import com.example.data.model.QuestionEntity
import com.example.data.model.ScoringRubricEntity
import com.example.data.model.WisdomSourceEntity

object DatabaseSeedData {

    suspend fun seedDatabase(database: MaryadaDatabase) {
        val wisdomSources = getInitialWisdomSources()
        database.wisdomSourceDao().insertAll(wisdomSources)

        val questions = getInitialQuestions()
        database.questionDao().insertAll(questions)

        val rubrics = getInitialRubrics()
        database.scoringRubricDao().insertAll(rubrics)
    }

    fun getInitialWisdomSources(): List<WisdomSourceEntity> = listOf(
        WisdomSourceEntity(
            id = "src_vow_dasharatha",
            textOrEpisodeTitle = "Rama Honoring the Vow of King Dasharatha",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Ayodhya Kanda, Sarga 19 & 21",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "When Kaikeyi commanded that Rama go into fourteen years of exile and Bharata be crowned, Rama accepted without a trace of resentment, placing his father's honor and truth above personal ambition and royal comfort.",
            relevantPrinciples = "Satya, Dharma, Samyama, Pitru-Vakya",
            interpretationNotes = "Demonstrates that true nobility lies in honoring promises and duty even when it demands immense personal sacrifice, refusing to let entitlement cloud dharma."
        ),
        WisdomSourceEntity(
            id = "src_lakshmana_anger",
            textOrEpisodeTitle = "Pacification of Lakshmana's Raging Anger",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Ayodhya Kanda, Sarga 21, Shloka 3-15",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "When Lakshmana drew his bow in fury, threatening to overthrow Dasharatha and slay anyone who blocked Rama's coronation, Rama gently pacified him, teaching that anger blinds righteousness and that filial duty cannot be fulfilled through violence.",
            relevantPrinciples = "Samyama, Viveka, Karuna, Ahimsa",
            interpretationNotes = "Shows the distinction between passionate impulse (Krodha) and dharmic composure. Righteous outrage must be channeled into wisdom rather than destructive retribution."
        ),
        WisdomSourceEntity(
            id = "src_bharata_chitrakuta",
            textOrEpisodeTitle = "Bharata at Chitrakuta Refusing the Crown",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Ayodhya Kanda, Sarga 112",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "Bharata journeyed to the forest to implore Rama to return and claim his rightful throne. When Rama stood firm on his duty, Bharata accepted only Rama's wooden sandals (Padukas) to place on the throne, ruling solely as a humble trustee.",
            relevantPrinciples = "Tyaga, Nishtha, Ananya Bhakti, Nyaya",
            interpretationNotes = "The supreme model of stewardship over ownership. Power is held strictly as a sacred trust for the people, free from personal ego or entitlement."
        ),
        WisdomSourceEntity(
            id = "src_kewat_boatman",
            textOrEpisodeTitle = "Kewat the Boatman Crossing the Ganga",
            traditionOrVersion = "Valmiki Ramayana & Ramcharitmanas",
            sourceReference = "Ayodhya Kanda, Sarga 52 & Ayodhya Kand Chaupai",
            translationOrEdition = "Ramcharitmanas, Gita Press Edition",
            summary = "When Kewat, an ordinary boatman, insisted on washing Rama's feet before ferrying him across the river, Rama accepted with overflowing humility, embracing Kewat as an equal brother and honoring his genuine devotion above worldly status.",
            relevantPrinciples = "Karuna, Samadarshana, Humility, Respect",
            interpretationNotes = "Rama dismantles social hierarchies and prejudice, illustrating that dignity and worth stem from character and sincerity, not status or caste."
        ),
        WisdomSourceEntity(
            id = "src_golden_deer",
            textOrEpisodeTitle = "The Illusion of the Golden Deer (Maricha)",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Aranya Kanda, Sarga 43 & 44",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "The demon Maricha took the illusory guise of a dazzling golden deer to lure Rama away from the hermitage, appealing to wonder and covetous desire, which precipitated the abduction of Sita.",
            relevantPrinciples = "Viveka, Samyama, Foresight, Skepticism",
            interpretationNotes = "A profound allegorical lesson on how superficial allure and unexamined desires can distract even the noble from vigilance, warning against glittering shortcuts and false appearances."
        ),
        WisdomSourceEntity(
            id = "src_jatayu_sacrifice",
            textOrEpisodeTitle = "Jatayu's Supreme Sacrifice Defending Sita",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Aranya Kanda, Sarga 67 & 68",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "Though aged and knowing he was hopelessly outmatched by Ravana's celestial weaponry, the venerable bird-king Jatayu fought valiantly to rescue Sita, giving his life in defense of the helpless. Rama performed his last rites as his own father.",
            relevantPrinciples = "Dhairya, Karuna, Duty beyond capacity, Martyrdom",
            interpretationNotes = "Exemplifies that duty calls us to stand against injustice regardless of the probability of personal victory. Sincerity of effort in protecting the innocent confers highest spiritual honor."
        ),
        WisdomSourceEntity(
            id = "src_shabari_hospitality",
            textOrEpisodeTitle = "Shabari's Pure Devotion and the Forest Berries",
            traditionOrVersion = "Valmiki Ramayana & Ramcharitmanas",
            sourceReference = "Aranya Kanda, Sarga 74",
            translationOrEdition = "Ramcharitmanas Aranya Kanda",
            summary = "The hermit Shabari, an elderly tribal woman, offered forest berries she had tasted to ensure their sweetness. Rama received her hospitality with deep reverence and joy, seeing only the purity of her heart.",
            relevantPrinciples = "Karuna, Samadarshana, Unconditional Love",
            interpretationNotes = "Rama judges actions by the intention and love behind them, transcending formal ritual purity or social orthodoxy."
        ),
        WisdomSourceEntity(
            id = "src_sugriva_alliance",
            textOrEpisodeTitle = "Covenant of Friendship between Rama and Sugriva",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Kishkindha Kanda, Sarga 5 & 8",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "Rama and the exiled Vanara king Sugriva consecrated their mutual alliance before the sacred fire, pledging mutual loyalty in their shared grief and trials without exploitation or deceit.",
            relevantPrinciples = "Mitrata, Nishtha, Nyaya, Reciprocal Loyalty",
            interpretationNotes = "Friendship must be anchored in mutual ethical responsibility, where allies stand by each other in suffering and hold each other accountable to justice."
        ),
        WisdomSourceEntity(
            id = "src_vali_vadha",
            textOrEpisodeTitle = "The Slaying of Vali and the Discourse on Justice",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Kishkindha Kanda, Sarga 18",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "When the mortally wounded Vali questioned the ethics of Rama striking him from behind trees, Rama explained the moral grounds: Vali had exiled his brother Sugriva, seized his brother's wife Ruma unjustly, and operated outside the codes of righteous warfare.",
            relevantPrinciples = "Nyaya, Rajadharma, Accountability for Power",
            interpretationNotes = "Addresses one of the most debated moral episodes, illustrating that leadership demands confronting structural injustice and upholding family boundaries, even when conventional aesthetics of conflict are challenged."
        ),
        WisdomSourceEntity(
            id = "src_vibhishana_sharanagati",
            textOrEpisodeTitle = "Vibhishana Seeking Asylum (Sharanagati)",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Yuddha Kanda, Sarga 17 & 18",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "When Ravana's brother Vibhishana defected and sought refuge, Rama's commanders, including Sugriva, suspected a Trojan horse and urged rejection. Rama declared his immortal vow: 'Even if Ravana himself came seeking shelter, I would never turn him away.'",
            relevantPrinciples = "Sharanagati, Karuna, Abhaya, Universal Compassion",
            interpretationNotes = "The pinnacle of ethical magnanimity: a righteous leader never closes the door on anyone genuinely seeking peace, even a former enemy, choosing trust and moral principle over cynical suspicion."
        ),
        WisdomSourceEntity(
            id = "src_samudra_reconciliation",
            textOrEpisodeTitle = "Rama and the Ocean Deity (Samudra)",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Yuddha Kanda, Sarga 21 & 22",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "Rama fasted on the shore for three days praying for the ocean to grant passage. When treated with indifference, Rama raised his bow in disciplined assertion. When Samudra appeared seeking forgiveness, Rama respected nature's boundaries and built the bridge (Setu) cooperatively.",
            relevantPrinciples = "Patience before firmness, Restraint of power, Environmental harmony",
            interpretationNotes = "Shows that strength must be preceded by patience and diplomacy. Power is applied with surgical restraint only when peaceful appeals are exhausted, and once cooperation is offered, wrath ceases immediately."
        ),
        WisdomSourceEntity(
            id = "src_hanuman_lanka_mission",
            textOrEpisodeTitle = "Hanuman's Diplomatic Restraint in Lanka",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Sundara Kanda, Sarga 51 - 54",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "When captured in Ravana's court, Hanuman endured insults and torture with immense composure, using his position not for reckless rage but to deliver a measured warning of counsel urging Ravana to avoid ruin by returning Sita.",
            relevantPrinciples = "Viveka, Samyama, Diplomatic Courage, Loyalty",
            interpretationNotes = "True valor is governed by intellectual restraint and discipline. Even in the enemy's den, the wise messenger prioritizes peace and de-escalation over bravado."
        ),
        WisdomSourceEntity(
            id = "src_ravana_dying_wisdom",
            textOrEpisodeTitle = "Lakshmana Learning Statesmanship from Dying Ravana",
            traditionOrVersion = "Ramcharitmanas & Regional Ramayana Traditions",
            sourceReference = "Lanka Kanda Tradition, Adhyatma Ramayana",
            translationOrEdition = "Traditional Commentary",
            summary = "As Ravana lay dying on the battlefield, Rama instructed Lakshmana to sit at the fallen monarch's feet with hands folded and learn statecraft, diplomacy, and the pitfalls of arrogance from a scholar of supreme intellect.",
            relevantPrinciples = "Humility, Recognition of Knowledge, Absence of Spite",
            interpretationNotes = "Teaches that death extinguishes animosity, and wisdom must be respected wherever it resides. Even an adversary has lessons to impart on the perils of uncontrolled ego."
        ),
        WisdomSourceEntity(
            id = "src_mandodari_respect",
            textOrEpisodeTitle = "Post-War Dignity and Cremation of Ravana",
            traditionOrVersion = "Valmiki Ramayana",
            sourceReference = "Yuddha Kanda, Sarga 111 - 114",
            translationOrEdition = "Critical Edition, Gita Press",
            summary = "When Vibhishana hesitated to perform Ravana's last rites due to his brother's unrighteous deeds, Rama intervened: 'Hostilities end with death. He is as much my brother as yours. Conduct his rites with full imperial honors.'",
            relevantPrinciples = "Maryada, Dignity, Transcending Enmity",
            interpretationNotes = "Righteous warfare never descends into personal hatred or post-victory desecration. The ethical victor honors the humanity of the vanquished."
        )
    )

    fun getInitialQuestions(): List<QuestionEntity> = QuestionDataRegistry.getAllScenarios()

    fun getInitialRubrics(): List<ScoringRubricEntity> = QuestionDataRegistry.getAllRubrics()
}
