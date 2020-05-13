package com.example.animalcrossinghandbook.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villagers")
data class Villager(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val gender: String,
    val personality: String,
    val birthday: String,
    val species: String,
    val catchPhrase: String?,
    val filename: String,
    var isResident: Boolean = false

) {

}