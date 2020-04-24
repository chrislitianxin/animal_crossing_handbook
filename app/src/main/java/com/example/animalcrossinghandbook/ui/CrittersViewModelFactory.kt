package com.example.animalcrossinghandbook.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.entities.BugDao
import com.example.animalcrossinghandbook.entities.FishDao

/* Boiler code pretty much */

class CrittersViewModelFactory(
    private val dataSourceBug: BugDao,
    private val dataSourceFish: FishDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrittersViewModel::class.java)) {
            return CrittersViewModel(dataSourceBug, dataSourceFish, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}