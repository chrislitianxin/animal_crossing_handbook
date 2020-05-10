package com.example.animalcrossinghandbook.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.data.VillagerDao
import com.example.animalcrossinghandbook.data.VillagerRepository
import kotlinx.coroutines.*

/**
 * The ViewModel for [VillagersFragment].
 */
class VillagersViewModel(
    private val repo: VillagerRepository,
    private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    val villagers: LiveData<List<Villager>> = repo.getAll()


    /**
     * Toggle switch to mark residents of island
     */
    fun toggleIsResidentById(villagerId: Int) {
        viewModelScope.launch {
            repo.toggleIsResident(villagerId)
        }
    }


}




