package com.ostapr.roomwithview.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ostapr.roomwithview.db.dao.WordDao
import com.ostapr.roomwithview.db.entitity.Word

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false,
)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract val wordDao: WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatabase::class.java,
                "word_database"
            ).build().also {
                INSTANCE = it
            }
        }
    }
}