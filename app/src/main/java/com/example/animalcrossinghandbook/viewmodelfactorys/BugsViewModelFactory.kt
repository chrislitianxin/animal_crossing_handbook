package com.example.animalcrossinghandbook.viewmodelfactorys

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.BugDao
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel

/* Boiler code pretty much */

class BugsViewModelFactory(
    private val dataSource: BugDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BugsViewModel::class.java)) {
            return BugsViewModel(
                dataSource, application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}