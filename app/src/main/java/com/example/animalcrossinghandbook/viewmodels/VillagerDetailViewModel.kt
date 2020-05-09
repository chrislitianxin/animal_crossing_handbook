package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugDao
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.data.VillagerDao
import kotlinx.coroutines.*

class VillagerDetailViewModel(
    private val id: Int = 0,
    dataSource: VillagerDao
) : ViewModel() {
    /**
     * TODO should refactor this to use repository for data access, using mediator live data is a workaround
     * reference https://stackoverflow.com/questions/49602606/how-can-we-assign-livedata-from-room-to-mutablelivedata-within-viewmodel
     */
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val database = dataSource

    // reference to db
    val villager: LiveData<Villager>
        get() = _villager
    private val _villager = MediatorLiveData<Villager>()
    // needed this to avoid cannot find symbol error


    init {
        _villager.addSource(database.getById(id),_villager::setValue)
    }



    // update  TODO implement resident
    private suspend fun update(villager: Villager) {
        withContext(Dispatchers.IO) {
            database.update(villager)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}