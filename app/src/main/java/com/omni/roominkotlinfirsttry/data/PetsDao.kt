package com.omni.roominkotlinfirsttry.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface PetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pet: Pet)

    @Delete
    fun delete(pet: Pet)

    @Query("select * from Pet order by name ASC")
    fun getAllPets():LiveData<List<Pet>>
}