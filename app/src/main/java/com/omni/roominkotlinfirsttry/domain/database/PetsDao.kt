package com.omni.roominkotlinfirsttry.domain.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omni.roominkotlinfirsttry.entities.PetEntity

@Dao
interface PetsDao {

    @Query("select * from PetEntity order by name ASC")
    fun observeAllPets(): LiveData<List<PetEntity>>

    @Query("select * from PetEntity order by name ASC")
    fun getAllPets(): List<PetEntity>

    @Query("select * from PetEntity where entryid=:petId")
    fun observePetById(petId: String): LiveData<PetEntity>

    @Query("select * from PetEntity where entryid=:petId")
    fun getPetById(petId: String): PetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: PetEntity)

    @Update
    suspend fun update(pet: PetEntity): Int

    @Query("DELETE From PetEntity where entryid=:petId")
    suspend fun deletePetById(petId: String): Int

    @Delete
    suspend fun deleteAll(pets: List<PetEntity>)

}