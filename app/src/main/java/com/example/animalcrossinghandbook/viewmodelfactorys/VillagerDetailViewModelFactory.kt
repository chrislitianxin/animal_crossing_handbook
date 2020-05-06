package com.example.animalcrossinghandbook.viewmodelfactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.VillagerDao
import com.example.animalcrossinghandbook.viewmodels.VillagerDetailViewModel

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 */
class VillagerDetailViewModelFactory(
    private val id: Int,
    private val dataSource: VillagerDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VillagerDetailViewModel::class.java)) {
            return VillagerDetailViewModel(id, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
