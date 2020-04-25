package com.example.animalcrossinghandbook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Embedded

@Entity(tableName = "fish")
data class Fish(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "shadow")
    val shadow: String?,

    @ColumnInfo(name = "price")
    val price: Int?,

    @Embedded
    val availability: Availability,

    @ColumnInfo(name = "filename")
    val filename: String?,

    @ColumnInfo(name = "in_museum")
    val in_museum: Boolean = false

) {


}

