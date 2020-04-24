package com.example.animalcrossinghandbook.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*
import com.example.animalcrossinghandbook.entities.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.entities.BugDao
import com.example.animalcrossinghandbook.entities.FishDao

class CrittersViewModel (
    private val bugDao: BugDao,
    private val fishDao: FishDao,
    application: Application) :  AndroidViewModel(application) {

//    /**
//     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
//     */
//    private var viewModelJob = Job()
//
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val bugs = bugDao.getAll()
    val fish = fishDao.getAll()

}




