package com.example.animalcrossinghandbook.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
abstract class FishDao : BaseDao<Fish> {

    @Query("SELECT * FROM fish")
    abstract fun getAll(): LiveData<List<Fish>>

    @Query("SELECT * FROM fish WHERE id = :id")
    abstract fun getById(id: Int): LiveData<Fish>

    @Query("DELETE FROM fish")
    abstract fun clearAll()

    @Query("SELECT COUNT(id) FROM fish")
    abstract fun count(): Int

    @Query("SELECT inMuseum FROM fish WHERE id = :fishId")
    abstract fun isInMuseum(fishId: Int): LiveData<Boolean>

    @Query("UPDATE fish SET inMuseum = (inMuseum | 1) - (inMuseum & 1) WHERE id = :fishId")
    abstract suspend fun toggleInMuseum(fishId: Int)


}