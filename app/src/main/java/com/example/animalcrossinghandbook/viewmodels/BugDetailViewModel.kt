package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugDao
import kotlinx.coroutines.*
import timber.log.Timber

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
        _bug.addSource(database.getById(bugId), _bug::setValue)
    }

    /**
     * Toggle switch to mark an item in/out museum
     */
    fun toggleInMuseum() {
        _bug.value?.apply { in_museum = !in_museum }
        _bug.postValue(_bug.value) // force postValue to notify Observers
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}