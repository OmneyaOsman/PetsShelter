package com.omni.domain.repositories

import com.omni.domain.database.PetsDatabase
import com.omni.domain.database.petsDatabase
import com.omni.entities.PetEntity

val repository by lazy { LocalPetsDataSource() }

 interface PetsDataSource {

    fun getAllPets(): List<PetEntity>

    fun insertAPet(pet: PetEntity)

    fun deleteAPet(pet: PetEntity)

    fun deleteAllPets(pets: List<PetEntity>)

}

class LocalPetsDataSource(private val database: PetsDatabase = petsDatabase): PetsDataSource {

    override fun getAllPets(): List<PetEntity> = database.petsDao.getAllPets()

    override fun insertAPet(pet: PetEntity) = database.petsDao.insert(pet)

    override fun deleteAPet(pet: PetEntity) = database.petsDao.delete(pet)

    override fun deleteAllPets(pets: List<PetEntity>) = database.petsDao.deleteAll(pets)

}