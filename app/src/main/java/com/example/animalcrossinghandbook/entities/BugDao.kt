package com.example.animalcrossinghandbook.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BugDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg bug: Bug)

    @Update
    fun update(vararg bug: Bug)

    @Delete
    fun delete(vararg bug: Bug)

    @Query("SELECT * FROM bugs")
    fun getAll(): LiveData<List<Bug>>

    @Query("SELECT * FROM bugs WHERE id = :id")
    fun get(id: Int): Bug?

    @Query("DELETE FROM bugs")
    fun clearAll()

    @Query("SELECT COUNT(id) FROM bugs")
    fun count(): Int

}