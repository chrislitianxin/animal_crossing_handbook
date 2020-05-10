package com.example.animalcrossinghandbook.data

class FishRepository(
    private val fishDao: FishDao
) {


    fun getAll() = fishDao.getAll()

    fun getFish(id: Int) = fishDao.getById(id)

    fun isInMuseum(id: Int) = fishDao.isInMuseum(id)

    suspend fun toggleInMuseum(id: Int) {
        fishDao.toggleInMuseum(id)
    }


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: FishRepository? = null

        fun getInstance(fishDao: FishDao) =
            instance ?: synchronized(this) {
                instance ?: FishRepository(fishDao).also { instance = it }
            }
    }


}
