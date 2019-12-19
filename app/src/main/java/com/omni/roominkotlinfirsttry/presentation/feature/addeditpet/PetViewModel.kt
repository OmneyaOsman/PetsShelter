package com.omni.roominkotlinfirsttry.presentation.feature.addeditpet

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.entities.PetEntity
import kotlinx.coroutines.launch

typealias PetsResult = LiveData<PagedList<PetEntity>>

class PetViewModel(private val petsRepository: PetsDataSource = repository) : ViewModel() {


    fun savePet(petEntity: PetEntity) {
        petEntity.takeUnless { it.name.isBlank() }
                ?.let {
                    viewModelScope.launch {
                        petsRepository.insertAPet(it)
                    }
                }
    }


    fun deletePet(petId: Int) {
        viewModelScope.launch {
            petId.let {
                petsRepository.deleteAPetById(it)
            }
        }
    }
}