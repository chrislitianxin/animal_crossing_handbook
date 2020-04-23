package com.example.animalcrossinghandbook

import androidx.room.Database
import com.example.animalcrossinghandbook.entities.*
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AnimalCrossingDatabaseUnitTest {

    private lateinit var fishDao: FishDao
    private lateinit var bugDao: BugDao
    private lateinit var db: AnimalCrossingDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AnimalCrossingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        bugDao = db.bugDao()
        fishDao = db.fishDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndGetBug() {

        val testBug = Bug(
            1,
            "Common butterfly",
            160,
            Availability(
                "4am - 7pm",
                "9-6",
                "3-12",
                "Flying",
                "Common",
                false,
                false
            ),
            "common_butterfly"
        )

        bugDao.insert(testBug)
        val bug = bugDao.get(1)
        assertEquals(bug, testBug)
        assertEquals(bug?.name, "Common butterfly")

    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndGetFish() {

        val testFish = Fish(
            1,
            "Bitterling",
            "Smallest (1)",
            900,
            Availability(
                "",
                "11-3",
                "5-9",
                "River",
                "Common",
                true,
                false
            ),
            "common_butterfly"
        )

        fishDao.insert(testFish)
        assertEquals(fishDao.get(1), testFish)
        assertEquals(fishDao.get(1)?.name, "Bitterling")
    }
}
