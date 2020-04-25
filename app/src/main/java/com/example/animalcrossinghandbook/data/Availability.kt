package com.example.animalcrossinghandbook.data

import androidx.room.ColumnInfo

data class Availability(

    @ColumnInfo(name = "time")
    val time: String?,

    @ColumnInfo(name = "month_northern")
    val month_northern: String?,

    @ColumnInfo(name = "month_southern")
    val month_southern: String?,

    @ColumnInfo(name = "location")
    val location: String?,

    @ColumnInfo(name = "rarity")
    val rarity: String? = "Common",

    @ColumnInfo(name = "is_all_day")
    val is_all_day: Boolean? = true,

    @ColumnInfo(name = "is_all_year")
    val is_all_year: Boolean? = true

)