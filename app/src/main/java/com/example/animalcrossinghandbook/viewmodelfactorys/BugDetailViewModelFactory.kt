package com.example.animalcrossinghandbook.viewmodelfactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.data.BugDao
import com.example.animalcrossinghandbook.viewmodels.BugDetailViewModel

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 */
class BugDetailViewModelFactory(
    private val bugId: Int,
    private val dataSource: BugDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BugDetailViewModel::class.java)) {
            return BugDetailViewModel(bugId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
