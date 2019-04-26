package com.omni.domain.usescase

import androidx.lifecycle.MutableLiveData
import com.omni.domain.repositories.LocalPetsDataSource
import com.omni.domain.repositories.repository

class DeleteAllPetsUseCase(private val result: PetsResult,
                           private val emptyViewLiveData: MutableLiveData<Boolean>,
                           private val petsRepository: LocalPetsDataSource = repository){

    operator  fun invoke(){
        petsRepository.getAllPets()
                .takeUnless { it.isEmpty()}
                ?.let { petsRepository.deleteAllPets(it) }
                ?.also { emptyViewLiveData.postValue(true) }
                ?.also { result.postValue(ArrayList()) }
    }

}