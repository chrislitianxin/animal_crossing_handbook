package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.BugRepository
import kotlinx.coroutines.*

/**
 * The ViewModel used in [BugDetailFragment].
 */
class BugDetailViewModel(
    private val repo: BugRepository,
    private val bugId: Int = 0

) : ViewModel() {
    /**
     * refactored this to use repository for data access
     */

    val isInMuseum = repo.isInMuseum(bugId)

    val bug = repo.getBug(bugId)


    /**
     * Toggle switch to mark an item in/out museum
     */
    fun toggleInMuseum() {
        viewModelScope.launch {
            repo.toggleInMuseum(bugId)
        }
    }

}