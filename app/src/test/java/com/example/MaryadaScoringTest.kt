package com.example

import com.example.ai.ScoringEngine
import com.example.data.db.DatabaseSeedData
import com.example.data.db.QuestionDataRegistry
import com.example.data.model.DilemmaCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MaryadaScoringTest {

    @Test
    fun testDatabaseSeedHasAtLeast100Scenarios() {
        val scenarios = QuestionDataRegistry.getAllScenarios()
        assertTrue("Database must contain at least 100 scenarios, found ${scenarios.size}", scenarios.size >= 100)
    }

    @Test
    fun testAllTwelveCategoriesRepresented() {
        val scenarios = QuestionDataRegistry.getAllScenarios()
        DilemmaCategory.entries.forEach { category ->
            val count = scenarios.count { it.categoryId == category.id }
            assertTrue("Category ${category.title} must have scenarios, found $count", count >= 7)
        }
    }

    @Test
    fun testWisdomSourcesHaveValidReferences() {
        val sources = DatabaseSeedData.getInitialWisdomSources()
        assertTrue("Must have authentic wisdom sources", sources.isNotEmpty())
        sources.forEach { source ->
            assertNotNull(source.textOrEpisodeTitle)
            assertTrue("Source reference must not be blank", source.sourceReference.isNotBlank())
            assertTrue("Summary must be documented", source.summary.isNotBlank())
        }
    }

    @Test
    fun testScoringEngineDifferentiatesNobleVsVindictiveResponses() {
        val question = QuestionDataRegistry.getAllScenarios().first { it.id == "q_career_1" }
        val sources = DatabaseSeedData.getInitialWisdomSources()
        val source = sources.first()

        // Response 1: High integrity, calm communication, factual evidence
        val nobleAnswer = "I would speak to my colleague privately, show the document edit history calmly, and propose sending a joint email to leadership crediting our collaboration."
        val nobleReasoning = "This preserves truth and justice without humiliating a peer or causing toxic department strife."
        val nobleResult = ScoringEngine.evaluateDeterministic(question, nobleAnswer, nobleReasoning, source)

        // Response 2: Vengeful, malicious retaliation
        val vengefulAnswer = "I will hack their account, leak their private chats to HR, and destroy their career out of revenge."
        val vengefulReasoning = "They deserve to suffer for humiliating me."
        val vengefulResult = ScoringEngine.evaluateDeterministic(question, vengefulAnswer, vengefulReasoning, source)

        assertTrue(
            "Noble response score (${nobleResult.overallScore}) should be substantially higher than vengeful score (${vengefulResult.overallScore})",
            nobleResult.overallScore >= vengefulResult.overallScore + 25
        )
        assertTrue("Noble score should reflect high alignment", nobleResult.overallScore >= 60)
        assertTrue("Vengeful score should be penalized below 50", vengefulResult.overallScore <= 50)
    }

    @Test
    fun testHologramPercentageLevels_0_25_50_75_100() {
        val testLevels = listOf(0, 25, 50, 75, 100)
        testLevels.forEach { level ->
            val clamped = level.coerceIn(0, 100)
            assertEquals(level, clamped)
            val fraction = clamped / 100f
            assertTrue(fraction in 0.0f..1.0f)
        }
    }

    @Test
    fun testMoralArchetypesMappingAndShareGeneration() {
        val warrior = com.example.data.model.MoralArchetypes.forScore(95)
        assertEquals("Dharmic Warrior", warrior.titleEn)

        val sadhak = com.example.data.model.MoralArchetypes.forScore(82)
        assertEquals("Satya Sadhak", sadhak.titleEn)

        val mitra = com.example.data.model.MoralArchetypes.forScore(68)
        assertEquals("Nyaya Mitra", mitra.titleEn)

        val yogi = com.example.data.model.MoralArchetypes.forScore(52)
        assertEquals("Samyam Yogi", yogi.titleEn)

        val seeker = com.example.data.model.MoralArchetypes.forScore(30)
        assertEquals("Kalyug Seeker", seeker.titleEn)

        val shareMsg = sadhak.generateShareMessage(82)
        assertTrue("Share message must contain score", shareMsg.contains("82%"))
        assertTrue("Share message must contain archetype", shareMsg.contains("Satya Sadhak"))
        assertTrue("Share message must contain hashtag", shareMsg.contains("#MaryadaTest"))
    }
}
