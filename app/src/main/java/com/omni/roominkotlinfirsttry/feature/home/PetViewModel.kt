package com.omni.roominkotlinfirsttry.feature.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.omni.domain.repositories.PetsRepository
import com.omni.domain.repositories.repository
import com.omni.domain.usescase.PetsResult
import com.omni.entities.PetEntity
import com.omni.usescases.engine.toMutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PetViewModel(private val petsRepository: PetsRepository = repository,
                   val emptyViewLiveData: MutableLiveData<Boolean> = true.toMutableLiveData(),
                   val petsResult: PetsResult = ArrayList<PetEntity>().toMutableLiveData()
) : ViewModel() {

    /**Define a parentJob, and a coroutineContext.
     *  The coroutineContext, by default,
    uses the parentJob and the main dispatcher to create a new instance of a CoroutineScope based on the coroutineContext.
     */

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)


    init {
        Log.d("dataPet" ,petsRepository.getAllPets().value.toString())
        petsRepository.getAllPets().value?.takeUnless { it.isEmpty() }
//                .also {
//                    emptyViewLiveData.postValue(false)
//                }
    .let { petsResult.postValue(it) }

    }

    fun insert(petEntity: PetEntity) = scope.launch(Dispatchers.IO) {
        petsRepository.insertAPet(petEntity)
        Log.d("dataPet" ,"Model")

    }

    fun delete(petEntity: PetEntity) = scope.launch(Dispatchers.IO) {
        petsRepository.deleteAPet(petEntity)
    }

    fun deleteAll(pets: List<PetEntity>) = scope.launch(Dispatchers.IO) {
        petsRepository.deleteAllPets(pets)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}