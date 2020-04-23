package com.example.animalcrossinghandbook.entities

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg fish: Fish)

    @Update
    fun update(vararg fish: Fish)

    @Delete
    fun delete(vararg fish: Fish)

    @Query("SELECT * FROM fish")
    fun getAll(): LiveData<List<Fish>>

    @Query("SELECT * FROM fish WHERE id = :id")
    fun get(id: Int): Fish?

    @Query("DELETE FROM fish")
    fun clearAll()

    @Query("SELECT COUNT(id) FROM fish")
    fun count(): Int

}