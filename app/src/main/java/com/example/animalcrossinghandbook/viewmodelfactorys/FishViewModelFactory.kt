package com.example.animalcrossinghandbook.viewmodelfactorys

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.data.FishRepository
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel
import com.example.animalcrossinghandbook.viewmodels.FishViewModel

/**
 * Factory for creating a [FishViewModel] with a constructor that
 * takes a [FishRepository].
 */
class FishViewModelFactory(
    private val repository: FishRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return FishViewModel(repository, handle) as T
    }
}
