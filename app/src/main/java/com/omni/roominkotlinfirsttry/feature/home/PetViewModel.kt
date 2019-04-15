package com.omni.roominkotlinfirsttry.feature.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.omni.domain.repositories.PetsRepository
import com.omni.entities.PetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PetViewModel(application: Application) : AndroidViewModel(application) {

    /**Define a parentJob, and a coroutineContext. The coroutineContext, by default,
    uses the parentJob and the main dispatcher to create a new instance of a CoroutineScope based on the coroutineContext.
     */

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private var petsRepository: PetsRepository
    val allpets: LiveData<List<PetEntity>>

    init {
        petsRepository = PetsRepository(application)
        allpets = petsRepository.getAllPets()
    }



    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(petEntity: com.omni.entities.PetEntity) = scope.launch(Dispatchers.IO){
        petsRepository.insertAPet(petEntity)
    }

    fun delete(petEntity: com.omni.entities.PetEntity)= scope.launch(Dispatchers.IO) {
        petsRepository.deleteAPet(petEntity)
    }

    fun deleteAll(pets: List<com.omni.entities.PetEntity>) = scope.launch(Dispatchers.IO) {
        petsRepository.deleteAllPets(pets)
    }


}