package com.omni.roominkotlinfirsttry.presentation.feature.petdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omni.roominkotlinfirsttry.domain.engine.toMutableLiveData

class PetDetailsViewModelFactory(private val petId: Int) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetDetailsViewModel(petId.toMutableLiveData()) as T
    }
}