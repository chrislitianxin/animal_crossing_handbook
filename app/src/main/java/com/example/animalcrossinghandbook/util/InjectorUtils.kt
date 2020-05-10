package com.example.animalcrossinghandbook.util

import android.content.Context
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.viewmodelfactorys.BugDetailViewModelFactory

object InjectorUtils {
    private fun getBugRepository(context: Context): BugRepository {
        return BugRepository.getInstance(
            AnimalCrossingDatabase.getInstance(context.applicationContext).bugDao()
        )
    }


    fun provideBugDetailViewModelFactory(
        context: Context,
        bugId: Int
    ): BugDetailViewModelFactory {
        return BugDetailViewModelFactory(
            getBugRepository(context),
            bugId
        )
    }
}