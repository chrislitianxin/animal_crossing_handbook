package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossinghandbook.data.*
import kotlinx.coroutines.*

/**
 * The ViewModel used in [VillagerDetailFragment].
 */
class VillagerDetailViewModel(
    private val repo: VillagerRepository,
    private val villagerId: Int = 0
) : ViewModel() {


    val isResident = repo.isResident(villagerId)

    val villager = repo.getVillager(villagerId)


    /**
     * Toggle switch to mark a villager resident
     */
    fun toggleIsResident() {
        viewModelScope.launch {
            repo.toggleIsResident(villagerId)
        }
    }

}