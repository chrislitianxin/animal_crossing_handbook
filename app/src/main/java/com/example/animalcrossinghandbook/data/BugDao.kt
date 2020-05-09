package com.example.animalcrossinghandbook.data

import androidx.lifecycle.LiveData
import androidx.room.*

//data class BugMinimal(
//    val id: Int,
//    val name: String,
//    val price: Int,
//    val location: String,
//    val filename: String
//)

@Dao
abstract class BugDao : BaseDao<Bug> {

    @Query("SELECT * FROM bugs")
    abstract fun getAll(): LiveData<List<Bug>>

    @Query("SELECT * FROM bugs WHERE id = :id")
    abstract fun getById(id: Int): LiveData<Bug>

    @Query("SELECT * FROM bugs WHERE name = :name")
    abstract fun getByName(name: String): LiveData<Bug>

    @Query("DELETE FROM bugs")
    abstract fun clearAll()

    @Query("SELECT COUNT(id) FROM bugs")
    abstract fun count(): Int

    @Query("UPDATE bugs SET inMuseum = :inMuseum WHERE id = :id")
    abstract fun updateInMuseum(id: Int, inMuseum: Boolean)

    @Query("SELECT inMuseum FROM bugs WHERE id = :bugId")
    abstract fun isInMuseum(bugId: Int): LiveData<Boolean>


//    @Query("SELECT id, name, price, location, filename FROM bugs")
//    abstract fun getBugMinimal(): LiveData<BugMinimal>
}