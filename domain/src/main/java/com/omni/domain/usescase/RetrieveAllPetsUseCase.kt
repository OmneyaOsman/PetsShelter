package com.omni.domain.usescase

import android.arch.lifecycle.MutableLiveData
import com.omni.domain.repositories.PetsRepository
import com.omni.entities.PetEntity

typealias PetsResult = MutableLiveData<List<PetEntity>>

class RetrieveAllPetsUseCase(
        private val result: PetsResult,
        val emptyViewLiveData: MutableLiveData<Boolean>,
        private val petsRepository: PetsRepository) {


    operator fun invoke() {
        result.value.takeUnless { emptyViewLiveData.value ?: false }
                ?.takeUnless { it.isEmpty() }
                ?.also { emptyViewLiveData.postValue(true) }
    }

}
