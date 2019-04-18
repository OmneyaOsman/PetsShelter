package com.omni.domain.repositories

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import android.util.Log
import com.omni.domain.database.PetsDatabase
import com.omni.domain.database.petsDatabase
import com.omni.entities.PetEntity

 val repository by lazy { PetsRepository() }

/**the Repository implements the logic for deciding whether to fetch data from a network
 *  or use results cached in a local database.*/
class PetsRepository(private val database: PetsDatabase = petsDatabase) {

    fun getAllPets(): LiveData<List<PetEntity>> {
        Log.d("dataPet" ,database.isOpen.toString())
        return database.petsDao.getAllPets()
    }

    @WorkerThread
    suspend fun insertAPet(pet: PetEntity) {
        database.petsDao.insert(pet)
        Log.d("dataPet" ,"PetsRepository")
    }

    @WorkerThread
    suspend fun deleteAPet(pet: PetEntity) {

        database.petsDao.delete(pet)
    }

    @WorkerThread
    suspend fun deleteAllPets(pets: List<PetEntity>) {

        database.petsDao.deleteAll(pets)
    }

}