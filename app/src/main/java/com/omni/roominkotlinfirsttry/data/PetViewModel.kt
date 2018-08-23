package com.omni.roominkotlinfirsttry.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

public  class PetViewModel(application: Application) :AndroidViewModel(application) {
    private var petsRepository : PetsRepository ?=null
    val allpets :LiveData<List<PetEntity>>

    init {
        petsRepository = PetsRepository(application)
        allpets = petsRepository!!.getAllPets()

    }

    fun insert(petEntity: PetEntity){
        petsRepository?.insertAPet(petEntity)
    }
}