package com.example.animalcrossinghandbook.viewmodelfactorys

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel

/**
 * Factory for creating a [BugsViewModel] with a constructor that
 * takes a [BugRepository].
 */
class BugsViewModelFactory(
    private val repository: BugRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return BugsViewModel(repository, handle) as T
    }
}
