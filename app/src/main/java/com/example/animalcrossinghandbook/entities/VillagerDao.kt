package com.example.animalcrossinghandbook.entities

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VillagerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg villager: Villager)

    @Update
    fun update(vararg villager: Villager)

    @Delete
    fun delete(vararg villager: Villager)

    @Query("SELECT * FROM villagers")
    fun getAll(): LiveData<List<Villager>>

    @Query("SELECT * FROM villagers WHERE id = :id")
    fun get(id: Int): Villager?

    @Query("DELETE FROM villagers")
    fun clearAll()

    @Query("SELECT COUNT(id) FROM villagers")
    fun count(): Int

}