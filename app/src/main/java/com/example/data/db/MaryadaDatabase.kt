package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.AnalysisResultEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.SavedReflectionEntity
import com.example.data.model.ScoringRubricEntity
import com.example.data.model.UserResponseEntity
import com.example.data.model.WisdomSourceEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        QuestionEntity::class,
        WisdomSourceEntity::class,
        ScoringRubricEntity::class,
        UserResponseEntity::class,
        AnalysisResultEntity::class,
        SavedReflectionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MaryadaDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun wisdomSourceDao(): WisdomSourceDao
    abstract fun scoringRubricDao(): ScoringRubricDao
    abstract fun userResponseDao(): UserResponseDao
    abstract fun analysisResultDao(): AnalysisResultDao
    abstract fun reflectionDao(): ReflectionDao

    companion object {
        @Volatile
        private var INSTANCE: MaryadaDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MaryadaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MaryadaDatabase::class.java,
                    "maryada_wisdom_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            INSTANCE?.let { database ->
                                scope.launch(Dispatchers.IO) {
                                    DatabaseSeedData.seedDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                // Also ensure seed is run if empty on warm launch
                scope.launch(Dispatchers.IO) {
                    if (instance.questionDao().getCount() == 0) {
                        DatabaseSeedData.seedDatabase(instance)
                    }
                }
                instance
            }
        }
    }
}
