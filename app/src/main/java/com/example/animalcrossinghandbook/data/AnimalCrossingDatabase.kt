package com.example.animalcrossinghandbook.data

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animalcrossinghandbook.util.DATABASE_NAME
import com.example.animalcrossinghandbook.util.DATABASE_PATH


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
    abstract fun villagerDao(): VillagerDao


    companion object {

        @Volatile
        private var INSTANCE: AnimalCrossingDatabase? = null


        fun getInstance(context: Context): AnimalCrossingDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // check if null to avoid repetitively initialize database
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimalCrossingDatabase::class.java,
                        DATABASE_NAME
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