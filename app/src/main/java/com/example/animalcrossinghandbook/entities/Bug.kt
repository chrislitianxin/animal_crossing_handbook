package com.example.animalcrossinghandbook.entities

import androidx.room.*


@Entity(tableName = "bugs")
data class Bug(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

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