package com.example.animalcrossinghandbook.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.data.BugRepository
import com.example.animalcrossinghandbook.data.FishRepository
import com.example.animalcrossinghandbook.data.VillagerRepository
import com.example.animalcrossinghandbook.viewmodelfactorys.*

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

    /**
     * Fish
     */
    private fun getFishRepository(context: Context): FishRepository {
        return FishRepository.getInstance(
            AnimalCrossingDatabase.getInstance(context.applicationContext).fishDao()
        )
    }

    fun provideFishViewModelFactory(fragment: Fragment): FishViewModelFactory {
        val repository = getFishRepository(fragment.requireContext())
        return FishViewModelFactory(repository, fragment)
    }


    fun provideFishDetailViewModelFactory(
        context: Context, fishId: Int
    ): FishDetailViewModelFactory {
        return FishDetailViewModelFactory(getFishRepository(context), fishId)
    }


    /**
     * Villager
     */

    private fun getVillagerRepository(context: Context): VillagerRepository {
        return VillagerRepository.getInstance(
            AnimalCrossingDatabase.getInstance(context.applicationContext).villagerDao()
        )
    }

    fun provideVillagersViewModelFactory(fragment: Fragment): VillagersViewModelFactory {
        val repository = getVillagerRepository(fragment.requireContext())
        return VillagersViewModelFactory(repository, fragment)
    }


    fun provideVillagerDetailViewModelFactory(
        context: Context, villagerId: Int
    ): VillagerDetailViewModelFactory {
        return VillagerDetailViewModelFactory(getVillagerRepository(context), villagerId)
    }

}