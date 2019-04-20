package com.omni.domain.usescase

import androidx.lifecycle.MutableLiveData
import com.omni.domain.engine.remove
import com.omni.domain.repositories.PetsRepository
import com.omni.domain.repositories.repository
import com.omni.entities.PetEntity

class DeleteAPetUseCase(private val result: PetsResult,
                        private val emptyViewLiveData: MutableLiveData<Boolean>,
                        private val petsRepository: PetsRepository = repository) {

    operator fun invoke(petEntity: PetEntity) {
        petEntity.takeUnless { it.name.isBlank() }
                ?.let { petsRepository.deleteAPet(it)}
                ?.let { result.remove(petEntity) }

        result.takeIf {it.value?.isNullOrEmpty() !!}
                .also { emptyViewLiveData.postValue(true) }


    }
}