package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.data.FishRepository
import kotlinx.coroutines.launch

/**
 * The ViewModel for [FishFragment].
 */
class FishViewModel(
    private val repo: FishRepository,
    private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    val fish: LiveData<List<Fish>> = repo.getAll()


    /**
     * Toggle switch to mark an item in/out museum
     */
    fun toggleInMuseumById(fishId: Int) {
        viewModelScope.launch {
            repo.toggleInMuseum(fishId)
        }
    }


}




