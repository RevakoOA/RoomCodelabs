package com.ostapr.roomwithview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ostapr.roomwithview.db.entitity.Word
import com.ostapr.roomwithview.repo.WordRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val wordRepo: WordRepository): ViewModel() {

    val allWords: LiveData<List<Word>> = wordRepo.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        wordRepo.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}