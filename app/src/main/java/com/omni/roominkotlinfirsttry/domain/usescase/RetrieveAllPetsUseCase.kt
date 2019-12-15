package com.omni.roominkotlinfirsttry.domain.usescase

import androidx.lifecycle.MutableLiveData
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.presentation.feature.home.PetsResult


class RetrieveAllPetsUseCase(
        private val result: PetsResult,
        private val emptyViewLiveData: MutableLiveData<Boolean>,
        private val petsRepository: PetsDataSource = repository) {


    operator fun invoke() {
     
    }

}
