package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val categoryId: String,
    val title: String,
    val scenario: String,
    val context: String,
    val difficulty: String, // "Foundational", "Nuanced", "Profound"
    val suggestedDirections: String, // Pipe-separated or JSON list of sample thought starters
    val followUpQuestions: String, // Guidance for follow-up reflection
    val activeStatus: Boolean = true
)

@Entity(tableName = "wisdom_sources")
data class WisdomSourceEntity(
    @PrimaryKey val id: String,
    val textOrEpisodeTitle: String,
    val traditionOrVersion: String, // e.g. "Valmiki Ramayana", "Ramcharitmanas", "Kamba Ramayanam"
    val sourceReference: String, // e.g. "Ayodhya Kanda, Sarga 21, Shloka 3-12"
    val translationOrEdition: String, // e.g. "Gitapress / R.T.H. Griffith critical edition"
    val summary: String,
    val relevantPrinciples: String, // Comma-separated virtues
    val interpretationNotes: String
)

@Entity(tableName = "scoring_rubrics")
data class ScoringRubricEntity(
    @PrimaryKey val id: String,
    val questionId: String,
    val applicableDimensions: String, // Comma-separated dimension keys (e.g. "satya,dharma,nyaya")
    val dimensionWeightsJson: String, // Key-weight pairs JSON, e.g. {"satya": 1.2, "dharma": 1.5}
    val scoringCriteria: String, // Criteria explanation
    val reasonableAlternativeApproaches: String // Nuanced alternatives that Lord Ram or Ramayana principles advocate
)

@Entity(tableName = "user_responses")
data class UserResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: String,
    val questionId: String,
    val userAnswer: String,
    val userReasoning: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "analysis_results")
data class AnalysisResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val responseId: Long,
    val questionId: String,
    val overallScore: Int, // 0..100
    val dimensionScoresJson: String, // e.g. {"satya":85,"dharma":70}
    val alignedPrinciplesJson: String, // List of where user aligned
    val reflectOnJson: String, // List of what to reflect on / blind spots
    val consideredPath: String, // A more considered balanced path
    val sourceIdsJson: String, // Referenced wisdom sources
    val confidence: String, // "High", "Balanced", "Exploratory"
    val limitations: String,
    val isSaved: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_reflections")
data class SavedReflectionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val questionTitle: String,
    val categoryId: String,
    val scenario: String,
    val userAnswer: String,
    val userReasoning: String,
    val overallScore: Int,
    val primaryVirtue: String,
    val alignedSummary: String,
    val consideredPath: String,
    val episodeTitle: String,
    val episodeReference: String,
    val savedAt: Long = System.currentTimeMillis()
)
