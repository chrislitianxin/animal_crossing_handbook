package com.example.animalcrossinghandbook.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.viewmodelfactorys.BugDetailViewModelFactory
import com.example.animalcrossinghandbook.viewmodelfactorys.BugsViewModelFactory

object InjectorUtils {
    /**
     * Bug
     */
    private fun getBugRepository(context: Context): BugRepository {
        return BugRepository.getInstance(
            AnimalCrossingDatabase.getInstance(context.applicationContext).bugDao()
        )
    }

    fun provideBugsViewModelFactory(fragment: Fragment): BugsViewModelFactory {
        val repository = getBugRepository(fragment.requireContext())
        return BugsViewModelFactory(repository, fragment)
    }


    fun provideBugDetailViewModelFactory(
        context: Context, bugId: Int
    ): BugDetailViewModelFactory {
        return BugDetailViewModelFactory(getBugRepository(context), bugId)
    }

}