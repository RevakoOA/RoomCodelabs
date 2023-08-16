package com.ostapr.roomwithview.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ostapr.roomwithview.db.entitity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    suspend fun getAlphabetizedWordsSnapshot(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(words: List<Word>)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords(): Int

}