package com.ostapr.roomwithview.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ostapr.roomwithview.db.dao.WordDao
import com.ostapr.roomwithview.db.entitity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false,
)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract val wordDao: WordDao

    private class WordDatabasePopulationCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { db ->
                scope.launch {
                    populateDatabase(db.wordDao)
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAllWords()

            wordDao.insert(Word("Hello"))
            wordDao.insert(Word("World"))

            val words = buildList<Word> {
                for (i in 1..100) {
                    Word("Word $i")
                }
            }
            wordDao.insert(words)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context, appScope: CoroutineScope) =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabasePopulationCallback(appScope))
                    .build().also {
                        INSTANCE = it
                    }
            }
    }
}