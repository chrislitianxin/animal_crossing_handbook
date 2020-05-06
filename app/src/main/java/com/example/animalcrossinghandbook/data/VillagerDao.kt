package com.example.animalcrossinghandbook.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class VillagerDao : BaseDao<Villager> {

    @Query("SELECT * FROM villagers")
    abstract fun getAll(): LiveData<List<Villager>>

    @Query("SELECT * FROM villagers WHERE id = :id")
    abstract fun getById(id: Int): LiveData<Villager>

    @Query("SELECT * FROM villagers WHERE name = :name")
    abstract fun getByName(name: String): Villager?

    @Query("DELETE FROM villagers")
    abstract fun clearAll()

    @Query("SELECT COUNT(id) FROM villagers")
    abstract fun count(): Int


}