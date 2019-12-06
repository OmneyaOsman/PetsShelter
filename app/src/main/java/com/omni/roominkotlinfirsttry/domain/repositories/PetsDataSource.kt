package com.omni.roominkotlinfirsttry.domain.repositories

import androidx.lifecycle.LiveData
import com.omni.roominkotlinfirsttry.domain.Result
import com.omni.roominkotlinfirsttry.entities.PetEntity

interface PetsDataSource {

    fun observePets(): LiveData<Result<List<PetEntity>>>

    suspend fun getAllPets(): Result<List<PetEntity>>

    fun observePet(petId: String): LiveData<Result<PetEntity>>

   suspend fun getPetById(petId: String): Result<PetEntity>

    suspend fun insertAPet(pet: PetEntity):Long

    suspend fun updateAPet(pet: PetEntity)

    suspend fun deleteAPetById(petId: String)

    suspend fun deleteAllPets(pets: List<PetEntity>)

}