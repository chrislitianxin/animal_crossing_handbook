package com.example.animalcrossinghandbook.viewmodelfactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.data.FishRepository
import com.example.animalcrossinghandbook.viewmodels.FishDetailViewModel

/**
 * Factory for creating a [FishDetailViewModel] with a constructor that takes a [BugRepository]
 * and an ID for the current [Fish].
 */

class FishDetailViewModelFactory(
    private val fishRepository: FishRepository,
    private val fishId: Int
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FishDetailViewModel::class.java)) {
            return FishDetailViewModel(fishRepository, fishId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
