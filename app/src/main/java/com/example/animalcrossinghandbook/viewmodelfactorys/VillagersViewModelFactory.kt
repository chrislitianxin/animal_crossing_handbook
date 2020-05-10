package com.example.animalcrossinghandbook.viewmodelfactorys

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.animalcrossinghandbook.data.VillagerRepository
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel
import com.example.animalcrossinghandbook.viewmodels.VillagersViewModel

/**
 * Factory for creating a [VillagersViewModel] with a constructor that
 * takes a [VillagerRepository].
 */
class VillagersViewModelFactory(
    private val repository: VillagerRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return VillagersViewModel(repository, handle) as T
    }
}



