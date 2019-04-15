package com.omni.domain.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface PetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pet: com.omni.entities.PetEntity)

    @Delete
    fun delete(pet: com.omni.entities.PetEntity)

    @Delete
    fun deleteAll(pets: List<com.omni.entities.PetEntity>)

    @Query("select * from PetEntity order by name ASC")
    fun getAllPets():LiveData<List<com.omni.entities.PetEntity>>
}