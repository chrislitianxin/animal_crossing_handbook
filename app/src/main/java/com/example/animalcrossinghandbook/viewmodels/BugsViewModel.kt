package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugRepository
import kotlinx.coroutines.launch

/**
 * The ViewModel for [BugsFragment].
 */
class BugsViewModel(
    private val repo: BugRepository,
    private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    val bugs: LiveData<List<Bug>> = repo.getAll()


    /**
     * Toggle switch to mark an item in/out museum
     */
    fun toggleInMuseumById(bugId: Int) {
        viewModelScope.launch {
            repo.toggleInMuseum(bugId)
        }
    }


}




