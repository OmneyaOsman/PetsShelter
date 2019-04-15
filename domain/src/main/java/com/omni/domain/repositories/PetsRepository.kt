package com.omni.domain.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.omni.domain.database.PetsDao
import com.omni.domain.database.PetsDatabase

/**the Repository implements the logic for deciding whether to fetch data from a network
 *  or use results cached in a local database.*/
class PetsRepository(application: Application) {

    private val petsDao: PetsDao
    private val petsList: LiveData<List<com.omni.entities.PetEntity>>


    init {
        val petsDB = PetsDatabase.getDatabase(application)
        petsDao = petsDB.petsDao()
        petsList = petsDao.getAllPets()
    }

    fun getAllPets(): LiveData<List<com.omni.entities.PetEntity>> {
        return petsList
    }

    @WorkerThread
    suspend fun insertAPet(pet: com.omni.entities.PetEntity) {
        petsDao.insert(pet)
    }

    @WorkerThread
    suspend fun deleteAPet(pet: com.omni.entities.PetEntity) {

        petsDao.delete(pet)
    }

    @WorkerThread
    suspend fun deleteAllPets(pets: List<com.omni.entities.PetEntity>) {

        petsDao.deleteAll(pets)
    }

}