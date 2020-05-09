package com.example.animalcrossinghandbook.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.data.VillagerDao
import kotlinx.coroutines.*

/**
 * @param database
 * @param application
 */
class VillagersViewModel(
    val database: VillagerDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // data
    val items = database.getAll()

    init {

    }


    // update  TODO implement resident
    private suspend fun update(villager: Villager) {
        withContext(Dispatchers.IO) {
            database.update(villager)
        }
    }


    /**
     * encapsulated live data changes when navigating
     */
    private val _navigateToItemDetail = MutableLiveData<Int>()

    fun onItemDetailNavigated() {
        _navigateToItemDetail.value = null
    }


    /**
     * Called when the ViewModel is dismantled.
     * At this point, we want to cancel all coroutines;
     * otherwise we end up with processes that have nowhere to return to
     * using memory and resources.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}




