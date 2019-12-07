package com.omni.roominkotlinfirsttry.domain.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.omni.roominkotlinfirsttry.domain.Result
import com.omni.roominkotlinfirsttry.entities.PetEntity

interface PetsDataSource {

    fun observePets(): LiveData<Result<List<PetEntity>>>

    fun observePet(petId: Int): LiveData<Result<PetEntity>>

    suspend fun getAllPets(): Result<List<PetEntity>>

    suspend fun retrievePets(): Result<LiveData<PagedList<PetEntity>>>

    suspend fun getPetById(petId: Int): Result<PetEntity>

    suspend fun insertAPet(pet: PetEntity): Long

    suspend fun updateAPet(pet: PetEntity)

    suspend fun deleteAPetById(petId: Int)

    suspend fun deleteAllPets(pets: List<PetEntity>)

}