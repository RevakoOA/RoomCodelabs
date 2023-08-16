package com.ostapr.roomwithview.ui

import android.app.Application
import com.ostapr.roomwithview.db.WordRoomDatabase
import com.ostapr.roomwithview.repo.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class WordApplication: Application() {

    val appScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getInstance(this, appScope) }
    val repository by lazy { WordRepository(database.wordDao) }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        appScope.cancel()
        super.onTerminate()
    }
}