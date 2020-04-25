package com.example.animalcrossinghandbook.data

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        Bug::class,
        Fish::class,
        Villager::class
    ],

    version = 1,
    exportSchema = true
)
//@TypeConverters(ListStringConverter::class)
abstract class AnimalCrossingDatabase : RoomDatabase() {

    abstract fun bugDao(): BugDao
    abstract fun fishDao(): FishDao

    companion object {

        @Volatile
        private var INSTANCE: AnimalCrossingDatabase? = null
        private const val DATABASE_PATH = "database/ac_database.db"

        fun getInstance(context: Context): AnimalCrossingDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // check if null to avoid repetitively initialize database
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimalCrossingDatabase::class.java,
                        "animal_crossing_database"
                    )
                        .createFromAsset(DATABASE_PATH)
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }


    }
}