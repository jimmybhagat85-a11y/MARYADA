package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.AnalysisResultEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.SavedReflectionEntity
import com.example.data.model.ScoringRubricEntity
import com.example.data.model.UserResponseEntity
import com.example.data.model.WisdomSourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE activeStatus = 1 ORDER BY id ASC")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE categoryId = :categoryId AND activeStatus = 1")
    fun getQuestionsByCategory(categoryId: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE id = :id LIMIT 1")
    suspend fun getQuestionById(id: String): QuestionEntity?

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: QuestionEntity)

    @Update
    suspend fun update(question: QuestionEntity)

    @Query("SELECT * FROM questions WHERE scenario LIKE '%' || :query || '%' OR title LIKE '%' || :query || '%'")
    fun searchQuestions(query: String): Flow<List<QuestionEntity>>
}

@Dao
interface WisdomSourceDao {
    @Query("SELECT * FROM wisdom_sources")
    fun getAllSources(): Flow<List<WisdomSourceEntity>>

    @Query("SELECT * FROM wisdom_sources WHERE id = :id LIMIT 1")
    suspend fun getSourceById(id: String): WisdomSourceEntity?

    @Query("SELECT * FROM wisdom_sources WHERE id IN (:ids)")
    suspend fun getSourcesByIds(ids: List<String>): List<WisdomSourceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sources: List<WisdomSourceEntity>)
}

@Dao
interface ScoringRubricDao {
    @Query("SELECT * FROM scoring_rubrics WHERE questionId = :questionId LIMIT 1")
    suspend fun getRubricForQuestion(questionId: String): ScoringRubricEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rubrics: List<ScoringRubricEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rubric: ScoringRubricEntity)
}

@Dao
interface UserResponseDao {
    @Insert
    suspend fun insert(response: UserResponseEntity): Long

    @Query("SELECT * FROM user_responses WHERE sessionId = :sessionId ORDER BY createdAt DESC")
    fun getResponsesForSession(sessionId: String): Flow<List<UserResponseEntity>>
}

@Dao
interface AnalysisResultDao {
    @Insert
    suspend fun insert(result: AnalysisResultEntity): Long

    @Query("SELECT * FROM analysis_results WHERE responseId = :responseId LIMIT 1")
    suspend fun getResultForResponse(responseId: Long): AnalysisResultEntity?
}

@Dao
interface ReflectionDao {
    @Query("SELECT * FROM saved_reflections ORDER BY savedAt DESC")
    fun getAllReflections(): Flow<List<SavedReflectionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reflection: SavedReflectionEntity): Long

    @Delete
    suspend fun delete(reflection: SavedReflectionEntity)

    @Query("DELETE FROM saved_reflections WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM saved_reflections")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM saved_reflections")
    fun getReflectionCount(): Flow<Int>
}
