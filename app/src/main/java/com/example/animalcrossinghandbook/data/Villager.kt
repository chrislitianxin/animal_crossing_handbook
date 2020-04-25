package com.example.animalcrossinghandbook.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villagers")
data class Villager(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "personality")
    val personality: String,

    @ColumnInfo(name = "birthday")
    val birthday: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "catch_phrase")
    val catch_phrase: String?,

    @ColumnInfo(name = "filename")
    val filename: String,

    @ColumnInfo(name = "is_resident")
    val is_resident: Boolean = false

) {

}