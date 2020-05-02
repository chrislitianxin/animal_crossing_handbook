package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugDao
import kotlinx.coroutines.*

class BugDetailViewModel(
    private val bugId: Int = 0,
    dataSource: BugDao
) : ViewModel() {
    /**
     * TODO should refactor this to use repository for data access, using mediator live data is a workaround
     * reference https://stackoverflow.com/questions/49602606/how-can-we-assign-livedata-from-room-to-mutablelivedata-within-viewmodel
     */
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val database = dataSource

    // reference to db
    val bug: LiveData<Bug>
        get() = _bug
    private val _bug = MediatorLiveData<Bug>()
    // needed this to avoid cannot find symbol error


    init {
        _bug.addSource(database.getById(bugId),_bug::setValue)
    }

    private fun initializeBugsData() {
        uiScope.launch {
            // update bugs data to display filtered
        }
    }


    // update bug TODO implement in_museum
    private suspend fun update(bug: Bug) {
        withContext(Dispatchers.IO) {
            database.update(bug)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}