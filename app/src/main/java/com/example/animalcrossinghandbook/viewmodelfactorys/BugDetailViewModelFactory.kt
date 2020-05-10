package com.example.animalcrossinghandbook.viewmodelfactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.viewmodels.BugDetailViewModel

/**
 * Factory for creating a [BugDetailViewModel] with a constructor that takes a [BugRepository]
 * and an ID for the current [Bug].
 */

class BugDetailViewModelFactory(
    private val bugRepository: BugRepository,
    private val bugId: Int
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BugDetailViewModel::class.java)) {
            return BugDetailViewModel(bugRepository, bugId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
