package com.omni.domain.usescase

import androidx.lifecycle.MutableLiveData
import com.omni.domain.repositories.PetsRepository
import com.omni.domain.repositories.repository
import com.omni.entities.PetEntity

typealias PetsResult = MutableLiveData<List<PetEntity>>

class RetrieveAllPetsUseCase(
        private val result: PetsResult,
        private val emptyViewLiveData: MutableLiveData<Boolean>,
        private val petsRepository: PetsRepository = repository) {


    operator fun invoke() {
        petsRepository.getAllPets()
                .also { emptyViewLiveData.postValue(true) }
                .takeUnless { it.isNullOrEmpty()}
                ?.also { emptyViewLiveData.postValue(false) }
                ?.also { result.postValue(it) } ?: result.postValue(ArrayList())
    }

}
