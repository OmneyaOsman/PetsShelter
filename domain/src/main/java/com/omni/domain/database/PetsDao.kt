package com.omni.domain.database

import androidx.room.*
import com.omni.entities.PetEntity

@Dao
interface PetsDao {

    @Query("select * from PetEntity order by name ASC")
    fun getAllPets(): List<PetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pet: PetEntity)

    @Delete
    fun delete(pet: PetEntity)

    @Delete
    fun deleteAll(pets: List<PetEntity>)


}