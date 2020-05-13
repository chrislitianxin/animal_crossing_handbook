package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.FishRepository
import kotlinx.coroutines.*

/**
 * The ViewModel used in [FishDetailFragment].
 */
class FishDetailViewModel(
    private val repo: FishRepository,
    private val fishId: Int = 0

) : ViewModel() {
    /**
     * refactored this to use repository for data access
     */

    val isInMuseum = repo.isInMuseum(fishId)

    val fish = repo.getFish(fishId)


    /**
     * Toggle switch to mark an item in/out museum
     */
    fun toggleInMuseum() {
        viewModelScope.launch {
            repo.toggleInMuseum(fishId)
        }
    }

}