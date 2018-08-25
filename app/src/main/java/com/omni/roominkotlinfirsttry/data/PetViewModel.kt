package com.omni.roominkotlinfirsttry.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

  class PetViewModel(application: Application) :AndroidViewModel(application) {
    private var petsRepository : PetsRepository ?=null
    val allpets :LiveData<List<PetEntity>>

    init {
        petsRepository = PetsRepository(application)
        allpets = petsRepository!!.getAllPets()
    }

      fun insert(petEntity: PetEntity){
        petsRepository?.insertAPet(petEntity) 
      }
      fun delete(petEntity: PetEntity){
        petsRepository?.deleteAPet(petEntity) 
      }
      fun deleteAll(pets: List<PetEntity>){
        petsRepository?.deleteAllPets(pets) 
      }


}