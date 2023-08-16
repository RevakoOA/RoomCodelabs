package com.ostapr.roomwithview.repo

import com.ostapr.roomwithview.db.dao.WordDao
import com.ostapr.roomwithview.db.entitity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}