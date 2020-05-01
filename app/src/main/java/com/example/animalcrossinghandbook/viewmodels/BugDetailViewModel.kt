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
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val database = dataSource

    // reference to db
    private val bug = MediatorLiveData<Bug>()
    // needed this to avoid cannot find symbol error
    fun getBug() = bug


    init {
        bug.addSource(database.getById(bugId),bug::setValue)
        //initializeBugsData()
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