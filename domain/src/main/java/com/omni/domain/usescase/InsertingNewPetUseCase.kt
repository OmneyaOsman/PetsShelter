package com.omni.domain.usescase

import androidx.lifecycle.MutableLiveData
import com.omni.domain.engine.add
import com.omni.domain.repositories.LocalPetsDataSource
import com.omni.domain.repositories.repository
import com.omni.entities.PetEntity

class InsertingNewPetUseCase(private val result: PetsResult,
                             private val emptyViewLiveData: MutableLiveData<Boolean>,
                             private val petsRepository: LocalPetsDataSource = repository) {

    operator fun invoke(petEntity: PetEntity) {
        petEntity.takeUnless { it.name.isBlank() }
                ?.also { emptyViewLiveData.postValue(false) }
                ?.let { petsRepository.insertAPet(it) }
                ?.also { result.add(petEntity) }
    }
}