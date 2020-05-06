package com.example.animalcrossinghandbook.viewmodelfactorys

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.VillagerDao
import com.example.animalcrossinghandbook.viewmodels.VillagersViewModel

/* Boiler code pretty much */

class VillagersViewModelFactory(
    private val dataSource: VillagerDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VillagersViewModel::class.java)) {
            return VillagersViewModel(
                dataSource, application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}