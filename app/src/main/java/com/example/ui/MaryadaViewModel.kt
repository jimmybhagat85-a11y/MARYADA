package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai.AnalysisOutput
import com.example.ai.ScoringEngine
import com.example.data.db.MaryadaDatabase
import com.example.data.model.DilemmaCategory
import com.example.data.model.QuestionEntity
import com.example.data.model.SavedReflectionEntity
import com.example.data.model.WisdomSourceEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    WELCOME, // Landing page: describes the 20-question journey and features
    TWENTY_QUESTIONS,
    SINGLE_QUESTION,
    RESULT,
    HOLOGRAM_LAB,
    SAVED_REFLECTIONS,
    WISDOM_EXPLORER,
    CATEGORIES
}

class MaryadaViewModel(application: Application) : AndroidViewModel(application) {

    private val db = MaryadaDatabase.getDatabase(application, viewModelScope)
    private val questionDao = db.questionDao()
    private val sourceDao = db.wisdomSourceDao()
    private val reflectionDao = db.reflectionDao()

    // Navigation state - starts on the Welcome Landing Page!
    private val _currentScreen = MutableStateFlow(AppScreen.WELCOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val screenBackStack = mutableListOf<AppScreen>()

    // Questions and sources from Room database
    val allQuestions: StateFlow<List<QuestionEntity>> = questionDao.getAllQuestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allSources: StateFlow<List<WisdomSourceEntity>> = sourceDao.getAllSources()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedReflections: StateFlow<List<SavedReflectionEntity>> = reflectionDao.getAllReflections()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Single Question flow state
    private val _currentQuestion = MutableStateFlow<QuestionEntity?>(null)
    val currentQuestion: StateFlow<QuestionEntity?> = _currentQuestion.asStateFlow()

    private val _userAnswer = MutableStateFlow("")
    val userAnswer: StateFlow<String> = _userAnswer.asStateFlow()

    private val _userReasoning = MutableStateFlow("")
    val userReasoning: StateFlow<String> = _userReasoning.asStateFlow()

    private val _showCustomDilemmaDialog = MutableStateFlow(false)
    val showCustomDilemmaDialog: StateFlow<Boolean> = _showCustomDilemmaDialog.asStateFlow()

    // 20-Questions Session state
    private val _twentyQuestions = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val twentyQuestions: StateFlow<List<QuestionEntity>> = _twentyQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    // Map of index (0..19) -> selected option / user answer
    private val _twentyAnswers = MutableStateFlow<Map<Int, String>>(emptyMap())
    val twentyAnswers: StateFlow<Map<Int, String>> = _twentyAnswers.asStateFlow()

    // Map of index (0..19) -> user custom reasoning / notes
    private val _twentyReasonings = MutableStateFlow<Map<Int, String>>(emptyMap())
    val twentyReasonings: StateFlow<Map<Int, String>> = _twentyReasonings.asStateFlow()

    // Selected choice index for current question (0..3)
    private val _selectedChoiceIndex = MutableStateFlow<Int?>(null)
    val selectedChoiceIndex: StateFlow<Int?> = _selectedChoiceIndex.asStateFlow()

    // Current answer and reasoning inputs
    private val _currentAnswerText = MutableStateFlow("")
    val currentAnswerText: StateFlow<String> = _currentAnswerText.asStateFlow()

    private val _currentReasoningText = MutableStateFlow("")
    val currentReasoningText: StateFlow<String> = _currentReasoningText.asStateFlow()

    // Analysis state
    private val _isAnalyzing = MutableStateFlow(false)
    val isAnalyzing: StateFlow<Boolean> = _isAnalyzing.asStateFlow()

    private val _analysisResult = MutableStateFlow<AnalysisOutput?>(null)
    val analysisResult: StateFlow<AnalysisOutput?> = _analysisResult.asStateFlow()

    private val _isCurrentResultSaved = MutableStateFlow(false)
    val isCurrentResultSaved: StateFlow<Boolean> = _isCurrentResultSaved.asStateFlow()

    // Hologram Lab state
    private val _labPercentage = MutableStateFlow(75)
    val labPercentage: StateFlow<Int> = _labPercentage.asStateFlow()

    // Category filter & Search query for Explorer
    private val _selectedCategory = MutableStateFlow<DilemmaCategory?>(null)
    val selectedCategory: StateFlow<DilemmaCategory?> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        // When questions are loaded, prepare the 20 randomized questions in background
        viewModelScope.launch {
            allQuestions.collect { list ->
                if (list.isNotEmpty() && _twentyQuestions.value.isEmpty()) {
                    prepareTwentyQuestions(list)
                }
            }
        }
    }

    fun prepareTwentyQuestions(questionsPool: List<QuestionEntity> = allQuestions.value) {
        if (questionsPool.isEmpty()) return
        val randomized20 = questionsPool.shuffled().take(20)
        _twentyQuestions.value = randomized20
        _currentQuestionIndex.value = 0
        _twentyAnswers.value = emptyMap()
        _twentyReasonings.value = emptyMap()
        _selectedChoiceIndex.value = null
        _currentAnswerText.value = ""
        _currentReasoningText.value = ""
        _analysisResult.value = null
        _isCurrentResultSaved.value = false
    }

    fun startNewTwentyQuestionsSession(questionsPool: List<QuestionEntity> = allQuestions.value) {
        prepareTwentyQuestions(questionsPool)
        navigateTo(AppScreen.TWENTY_QUESTIONS)
    }

    fun selectChoice(choiceIndex: Int, choiceText: String) {
        _selectedChoiceIndex.value = choiceIndex
        _currentAnswerText.value = choiceText
    }

    fun setCurrentAnswerText(text: String) {
        _currentAnswerText.value = text
        _selectedChoiceIndex.value = null
    }

    fun setCurrentReasoningText(text: String) {
        _currentReasoningText.value = text
    }

    fun nextQuestion() {
        val currentIndex = _currentQuestionIndex.value
        val ans = _currentAnswerText.value
        val rsn = _currentReasoningText.value

        // Save current answer & reasoning
        val updatedAnswers = _twentyAnswers.value.toMutableMap()
        updatedAnswers[currentIndex] = ans
        _twentyAnswers.value = updatedAnswers

        val updatedReasonings = _twentyReasonings.value.toMutableMap()
        updatedReasonings[currentIndex] = rsn
        _twentyReasonings.value = updatedReasonings

        if (currentIndex < 19) {
            val nextIndex = currentIndex + 1
            _currentQuestionIndex.value = nextIndex

            // Pre-fill next question's answer if user already answered it previously
            val existingAns = updatedAnswers[nextIndex] ?: ""
            val existingRsn = updatedReasonings[nextIndex] ?: ""
            _currentAnswerText.value = existingAns
            _currentReasoningText.value = existingRsn
            _selectedChoiceIndex.value = null
        } else {
            // Reached Question 20 -> Run aggregate analysis
            finishTwentyQuestionsAndAnalyze()
        }
    }

    fun previousQuestion() {
        val currentIndex = _currentQuestionIndex.value
        if (currentIndex > 0) {
            val prevIndex = currentIndex - 1
            _currentQuestionIndex.value = prevIndex
            _currentAnswerText.value = _twentyAnswers.value[prevIndex] ?: ""
            _currentReasoningText.value = _twentyReasonings.value[prevIndex] ?: ""
            _selectedChoiceIndex.value = null
        }
    }

    fun finishTwentyQuestionsAndAnalyze() {
        val questions = _twentyQuestions.value
        val answers = _twentyAnswers.value
        val reasonings = _twentyReasonings.value
        val sources = allSources.value

        _isAnalyzing.value = true
        _isCurrentResultSaved.value = false

        viewModelScope.launch {
            try {
                val output = ScoringEngine.analyzeBatch(
                    questions = questions,
                    answers = answers,
                    reasonings = reasonings,
                    wisdomSources = sources
                )
                _analysisResult.value = output
                _labPercentage.value = output.overallScore
                _isAnalyzing.value = false
                navigateTo(AppScreen.RESULT)
            } catch (e: Exception) {
                _isAnalyzing.value = false
            }
        }
    }

    // Single Dilemma Exploration methods
    fun toggleCustomDilemmaDialog(show: Boolean = true) {
        _showCustomDilemmaDialog.value = show
    }

    fun startQuestion(question: QuestionEntity) {
        _currentQuestion.value = question
        _userAnswer.value = ""
        _userReasoning.value = ""
        navigateTo(AppScreen.SINGLE_QUESTION)
    }

    fun setUserAnswer(text: String) {
        _userAnswer.value = text
    }

    fun setUserReasoning(text: String) {
        _userReasoning.value = text
    }

    fun applySuggestedDirection(direction: String) {
        _userAnswer.value = direction
    }

    fun submitForAnalysis() {
        val q = _currentQuestion.value ?: return
        val ans = _userAnswer.value
        val rsn = _userReasoning.value
        val sources = allSources.value

        _isAnalyzing.value = true
        _isCurrentResultSaved.value = false

        viewModelScope.launch {
            try {
                val output = ScoringEngine.analyze(
                    question = q,
                    userAnswer = ans,
                    userReasoning = rsn,
                    wisdomSources = sources
                )
                _analysisResult.value = output
                _labPercentage.value = output.overallScore
                _isAnalyzing.value = false
                navigateTo(AppScreen.RESULT)
            } catch (e: Exception) {
                _isAnalyzing.value = false
            }
        }
    }

    fun createAndStartCustomDilemma(
        title: String,
        scenario: String,
        context: String,
        category: DilemmaCategory
    ) {
        val customEntity = QuestionEntity(
            id = "custom_${System.currentTimeMillis()}",
            categoryId = category.id,
            title = title.ifBlank { "My Life Dilemma" },
            scenario = scenario,
            context = context,
            suggestedDirections = "Uphold truth and open dialogue|Choose long-term peace over immediate victory|Prioritize duty and loyalty to principle|Seek neutral wisdom before acting",
            difficulty = "Personal",
            followUpQuestions = "What core value is at stake in this decision?|How will this path affect those who place their trust in you?"
        )
        viewModelScope.launch {
            questionDao.insert(customEntity)
            _showCustomDilemmaDialog.value = false
            startQuestion(customEntity)
        }
    }

    fun saveCurrentReflection() {
        val res = _analysisResult.value ?: return

        viewModelScope.launch {
            val reflection = SavedReflectionEntity(
                questionTitle = "20-Prashna Maryada Journey (20 Questions Reflection)",
                categoryId = "cat_all",
                scenario = "Comprehensive ethical evaluation across 20 real-life randomized dilemmas.",
                userAnswer = "Completed 20 questions journey across Career, Family, Truth, Anger, Money, Leadership & Society.",
                userReasoning = "Evaluated against Lord Shri Ram's documented Ramayana virtues.",
                overallScore = res.overallScore,
                primaryVirtue = res.matchedSource.relevantPrinciples,
                alignedSummary = res.alignedPoints.firstOrNull() ?: "Alignment with Dharmic principles",
                consideredPath = res.consideredPath,
                episodeTitle = res.matchedSource.textOrEpisodeTitle,
                episodeReference = res.matchedSource.sourceReference
            )
            reflectionDao.insert(reflection)
            _isCurrentResultSaved.value = true
        }
    }

    fun navigateTo(screen: AppScreen) {
        if (_currentScreen.value != screen) {
            screenBackStack.add(_currentScreen.value)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        if (screenBackStack.isNotEmpty()) {
            _currentScreen.value = screenBackStack.removeAt(screenBackStack.size - 1)
            return true
        }
        if (_currentScreen.value != AppScreen.WELCOME) {
            _currentScreen.value = AppScreen.WELCOME
            return true
        }
        return false
    }

    fun setLabPercentage(percent: Int) {
        _labPercentage.value = percent.coerceIn(0, 100)
    }

    fun selectCategory(category: DilemmaCategory?) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun deleteReflection(reflection: SavedReflectionEntity) {
        viewModelScope.launch {
            reflectionDao.delete(reflection)
        }
    }

    fun clearAllReflections() {
        viewModelScope.launch {
            reflectionDao.clearAll()
        }
    }
}
