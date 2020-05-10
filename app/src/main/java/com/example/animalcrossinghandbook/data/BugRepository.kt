package com.example.animalcrossinghandbook.data

class BugRepository private constructor(
    private val bugDao: BugDao
) {

    fun getAll() = bugDao.getAll()

    fun getBug(id: Int) = bugDao.getById(id)

    fun isInMuseum(id: Int) = bugDao.isInMuseum(id)

    suspend fun toggleInMuseum(id: Int) {
        bugDao.toggleInMuseum(id)
    }


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: BugRepository? = null

        fun getInstance(bugDao: BugDao) =
            instance ?: synchronized(this) {
                instance ?: BugRepository(bugDao).also { instance = it }
            }
    }
}