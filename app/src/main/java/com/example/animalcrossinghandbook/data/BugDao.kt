package com.example.animalcrossinghandbook.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class BugDao : BaseDao<Bug> {

    @Query("SELECT * FROM bugs")
    abstract fun getAll(): LiveData<List<Bug>>

    @Query("SELECT * FROM bugs WHERE id = :id")
    abstract fun getById(id: Int): Bug?

    @Query("SELECT * FROM bugs WHERE name = :name")
    abstract fun getByName(name: String): Bug?

    @Query("DELETE FROM bugs")
    abstract fun clearAll()

    @Query("SELECT COUNT(id) FROM bugs")
    abstract fun count(): Int


}