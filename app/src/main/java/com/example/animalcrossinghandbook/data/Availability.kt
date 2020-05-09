package com.example.animalcrossinghandbook.data


data class Availability(

    val time: String?,
    val monthNorthern: String?,
    val monthSouthern: String?,
    val location: String?,
    val rarity: String? = "Common",
    val isAllDay: Boolean? = true,
    val isAllYear: Boolean? = true

)