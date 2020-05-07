package com.example.animalcrossinghandbook.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugDao
import kotlinx.coroutines.*
import timber.log.Timber

/**
 * @param database
 * @param application
 */
class BugsViewModel(
    val database: BugDao,
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
    val items = database.getAll()

    init {
        initializeBugsData()
    }

    private fun initializeBugsData() {
        uiScope.launch {
            // update bugs data to display filtered
            //items.value = getAllBugsFromDatabase()
        }
    }





    /**
     * encapsulated live data changes when navigating
     */
    private val _navigateToItemDetail = MutableLiveData<Int>()

    val navigateToItemDetail
        get() = _navigateToItemDetail

    fun onItemClicked(itemId: Int) {
        _navigateToItemDetail.value = itemId
    }

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




