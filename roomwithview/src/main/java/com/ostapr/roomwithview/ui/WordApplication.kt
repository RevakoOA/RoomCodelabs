package com.ostapr.roomwithview.ui

import android.app.Application
import com.ostapr.roomwithview.db.WordRoomDatabase
import com.ostapr.roomwithview.repo.WordRepository

class WordApplication: Application() {

    val database by lazy { WordRoomDatabase.getInstance(this) }
    val repository by lazy { WordRepository(database.wordDao) }

    override fun onCreate() {
        super.onCreate()
    }
}