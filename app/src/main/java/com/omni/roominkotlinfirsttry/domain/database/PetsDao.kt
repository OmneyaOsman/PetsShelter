package com.omni.roominkotlinfirsttry.domain.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.omni.roominkotlinfirsttry.entities.PetEntity

@Dao
interface PetsDao {

    @Query("select * from PetEntity order by name ASC")
    fun observeAllPets(): LiveData<List<PetEntity>>

    @Query("select * from PetEntity where entryid=:petId")
    fun observePetById(petId: Int): LiveData<PetEntity>

    @Query("select * from PetEntity")
    suspend fun getAllPets(): List<PetEntity>

    @Query("select * from PetEntity")
    suspend fun retrievePets(): DataSource.Factory<Int ,PetEntity>

    @Query("select * from PetEntity order by name ASC")
    suspend fun getOrderedPetsByName(): List<PetEntity>

    @Query("select * from PetEntity where entryid=:petId")
    suspend fun getPetById(petId: Int): PetEntity?

    @Query("select * from PetEntity where name=:petName")
    suspend fun findPetByName(petName: String): PetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: PetEntity): Long

    @Update
    suspend fun update(pet: PetEntity): Int

    @Query("DELETE From PetEntity where entryid=:petId")
    suspend fun deletePetById(petId: Int): Int

    @Delete
    suspend fun deleteAll(pets: List<PetEntity>)

}