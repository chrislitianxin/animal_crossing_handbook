package com.example.animalcrossinghandbook.data

import androidx.room.*


@Entity(tableName = "bugs")
data class Bug(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Int?,
    val filename: String?,
    var inMuseum: Boolean = false,

    @Embedded
    val availability: Availability


) {

}