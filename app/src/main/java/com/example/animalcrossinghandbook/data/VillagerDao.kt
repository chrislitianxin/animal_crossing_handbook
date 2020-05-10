package com.example.animalcrossinghandbook.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class VillagerDao : BaseDao<Villager> {

    @Query("SELECT * FROM villagers")
    abstract fun getAll(): LiveData<List<Villager>>

    @Query("DELETE FROM villagers")
    abstract fun clearAll()

    @Query("SELECT COUNT(id) FROM villagers")
    abstract fun count(): Int

    @Query("SELECT * FROM villagers WHERE id = :id")
    abstract fun getById(id: Int): LiveData<Villager>

    @Query("SELECT isResident FROM villagers WHERE id = :villagerId")
    abstract fun isResident(villagerId: Int): LiveData<Boolean>

    @Query("UPDATE villagers SET isResident = (isResident | 1) - (isResident & 1) WHERE id = :villagerId")
    abstract suspend fun toggleIsResident(villagerId: Int)

}