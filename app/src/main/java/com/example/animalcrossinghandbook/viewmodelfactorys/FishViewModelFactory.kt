package com.example.animalcrossinghandbook.viewmodelfactorys

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.FishDao
import com.example.animalcrossinghandbook.viewmodels.FishViewModel

/* Boiler code pretty much */

class FishViewModelFactory(
    private val dataSource: FishDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FishViewModel::class.java)) {
            return FishViewModel(
                dataSource, application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}