package com.example.animalcrossinghandbook.entities

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun toList(string: String): List<String> {
        return string.split(",")
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(",")
    }
}