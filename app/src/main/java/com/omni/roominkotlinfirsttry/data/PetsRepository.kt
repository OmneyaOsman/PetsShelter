package com.omni.roominkotlinfirsttry.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

/**the Repository implements the logic for deciding whether to fetch data from a network
 *  or use results cached in a local database.*/
public class PetsRepository(application: Application) {

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

   private  class InsertAsync (private val petsDao: PetsDao):AsyncTask<PetEntity , Void , Void>(){

         override fun doInBackground(vararg pet: PetEntity?): Void? {

             petsDao.insert(pet[0]!!)
             return null
         }
    }

}