package com.example.animalcrossinghandbook.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Dao
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugDao
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.data.FishDao
import kotlinx.coroutines.*
import timber.log.Timber

class FishViewModel(
    val database: FishDao,
    application: Application
) : AndroidViewModel(application) {

    /**
     * Coroutine to fetch data
     * job + dispatcher + scope
     *
     * * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [viewModelJob], any coroutine started in this uiScope can be cancelled
     * by calling `viewModelJob.cancel()`
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // data
    //private var items = MutableLiveData<Bug?>()
    val items = database.getAll()

    init {
        initializeFishData()
    }


    private fun initializeFishData() {
        uiScope.launch {
            // update bugs data to display filtered
            //items.value = getAllBugsFromDatabase()
        }
    }


    // update bug TODO implement in_museum
    private suspend fun update(fish: Fish) {
        withContext(Dispatchers.IO) {
            database.update(fish)
        }
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




