package com.example.animalcrossinghandbook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Embedded

@Entity(tableName = "fish")
data class Fish(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val shadow: String?,
    val price: Int?,
    val filename: String?,
    val inMuseum: Boolean = false,

    @Embedded
    val availability: Availability

) {


}

