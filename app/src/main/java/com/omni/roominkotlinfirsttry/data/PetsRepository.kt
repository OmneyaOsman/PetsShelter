package com.omni.roominkotlinfirsttry.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

/**the Repository implements the logic for deciding whether to fetch data from a network
 *  or use results cached in a local database.*/
class PetsRepository(application: Application) {

    private val petsDao: PetsDao
    private val petsList: LiveData<List<PetEntity>>


    init {
        val petsDB = PetsDatabase.getDatabase(application)
        petsDao = petsDB.petsDao()
        petsList = petsDao.getAllPets()
    }

    fun getAllPets(): LiveData<List<PetEntity>> {
        return petsList
    }

    @WorkerThread
    suspend fun insertAPet(pet: PetEntity) {
        petsDao.insert(pet)
    }

    @WorkerThread
    suspend fun deleteAPet(pet: PetEntity) {

        petsDao.delete(pet)
    }

    @WorkerThread
    suspend fun deleteAllPets(pets: List<PetEntity>) {

        petsDao.deleteAll(pets)
    }

}