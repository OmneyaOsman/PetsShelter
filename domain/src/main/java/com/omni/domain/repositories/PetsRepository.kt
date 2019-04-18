package com.omni.domain.repositories

import com.omni.domain.database.PetsDatabase
import com.omni.domain.database.petsDatabase
import com.omni.entities.PetEntity

val repository by lazy { PetsRepository() }


class PetsRepository(private val database: PetsDatabase = petsDatabase) {

    fun getAllPets(): List<PetEntity> = database.petsDao.getAllPets()

    fun insertAPet(pet: PetEntity) = database.petsDao.insert(pet)

    fun deleteAPet(pet: PetEntity) = database.petsDao.delete(pet)

    fun deleteAllPets(pets: List<PetEntity>) = database.petsDao.deleteAll(pets)

}