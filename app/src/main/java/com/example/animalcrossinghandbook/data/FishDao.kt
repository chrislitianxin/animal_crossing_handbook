package com.example.animalcrossinghandbook.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
abstract class FishDao : BaseDao<Fish> {

    @Query("SELECT * FROM fish")
    abstract fun getAll(): LiveData<List<Fish>>

    @Query("SELECT * FROM fish WHERE id = :id")
    abstract fun getById(id: Int): Fish?

    @Query("SELECT * FROM fish WHERE name = :name")
    abstract fun getByName(name: String): Fish?

    @Query("DELETE FROM fish")
    abstract fun clearAll()

    @Query("SELECT COUNT(id) FROM fish")
    abstract fun count(): Int

}