package com.example.animalcrossinghandbook.viewmodels

import androidx.lifecycle.*
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.BugDao
import kotlinx.coroutines.*
import timber.log.Timber

class BugDetailViewModel(
    id: Int = 0,
    dataSource: BugDao
) : ViewModel() {
    /**
     * TODO should refactor this to use repository for data access, using mediator live data is a workaround
     * reference https://stackoverflow.com/questions/49602606/how-can-we-assign-livedata-from-room-to-mutablelivedata-within-viewmodel
     */
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val database = dataSource
    val toggleInMuseumButtonText = "HAHA button"
    // reference to db
    //val bug = database.getById(id)
    val bug: LiveData<Bug>
        get() = _bug

    //private val _bug = MediatorLiveData<Bug>()
    // needed this to avoid cannot find symbol error
    private var _bug = MutableLiveData<Bug>()

    init {
        //_bug.addSource(database.getById(id), _bug::setValue)
        initializeDetailData(id)
    }

    private fun initializeDetailData(id: Int) {
        uiScope.launch {
            _bug.value = getBugDetail(id)
        }
    }

    private suspend fun getBugDetail(id: Int): Bug? {
        return withContext(Dispatchers.IO) {
            var bug = database.getById(id)

            bug.value
        }
    }

    /**
     * Toggle switch to mark an item in/out museum
     */
    fun toggleInMuseum() {
        uiScope.launch {
            _bug.value?.apply { in_museum = !in_museum }
            _bug.postValue(_bug.value) // force postValue to notify Observers
            _bug.value?.let { updateInMuseum(it) } // write to db
            val __bug = _bug.value
            Timber.i("$__bug")
        }
    }

    // update database to reflect change in in_museum field
    private suspend fun updateInMuseum(bug: Bug) {
        withContext(Dispatchers.IO) {
            database.updateInMuseum(bug.id, bug.in_museum)
        }
    }

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