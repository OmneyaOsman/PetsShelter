package com.omni.roominkotlinfirsttry.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

/**the Repository implements the logic for deciding whether to fetch data from a network
 *  or use results cached in a local database.*/
 class PetsRepository(application: Application) {

    private val petsDao :PetsDao
    private val petsList: LiveData<List<PetEntity>>


    init {
        val petsDB = PetsDatabase.getInstance(application)
        petsDao = petsDB.petsDao()
        petsList = petsDao.getAllPets()
    }

    fun getAllPets():LiveData<List<PetEntity>>{
        return petsList
    }

    fun insertAPet(pet:PetEntity){

        InsertAsync(petsDao).execute(pet)
    }
    fun deleteAPet(pet:PetEntity){

        DeleteAsync(petsDao).execute(pet)
    }
    fun deleteAllPets(pets:List<PetEntity>){

        DeleteALLAsync(petsDao).execute(pets)
    }

   private  class InsertAsync (private val petsDao: PetsDao):AsyncTask<PetEntity , Void , Void>(){

         override fun doInBackground(vararg pet: PetEntity?): Void? {

             petsDao.insert(pet[0]!!)
             return null
         }
    }

    private  class DeleteAsync (private val petsDao: PetsDao):AsyncTask<PetEntity , Void , Void>(){

         override fun doInBackground(vararg pet: PetEntity?): Void? {

             petsDao.delete(pet[0]!!)
             return null
         }
    }

    private  class DeleteALLAsync (private val petsDao: PetsDao):AsyncTask<List<PetEntity> , Void , Void>(){

         override fun doInBackground(vararg pets: List<PetEntity>?): Void? {

             petsDao.deleteAll(pets[0]!!)
             return null
         }
    }

}