package com.example.animalcrossinghandbook.data

class VillagerRepository private constructor(
    private val villagerDao: VillagerDao
) {

    fun getAll() = villagerDao.getAll()

    fun getVillager(id: Int) = villagerDao.getById(id)


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: VillagerRepository? = null

        fun getInstance(villagerDao: VillagerDao) =
            instance ?: synchronized(this) {
                instance ?: VillagerRepository(villagerDao).also { instance = it }
            }
    }

}