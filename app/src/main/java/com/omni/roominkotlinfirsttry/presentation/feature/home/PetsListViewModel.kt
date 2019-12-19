package com.omni.roominkotlinfirsttry.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.entities.PetEntity
import com.omni.roominkotlinfirsttry.entities.Result
import kotlinx.coroutines.launch

typealias PetsResult = LiveData<PagedList<PetEntity>>

class PetsListViewModel(private val petsRepository: PetsDataSource = repository) : ViewModel() {

    private var _payload: PetsResult = petsRepository.loadPets()
    val payload: PetsResult = _payload


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

    fun deleteAll() {
        viewModelScope.launch {
            petsRepository.getAllPets()
                    .let {
                        if (it is Result.Success)
                            petsRepository.deleteAllPets(it.data)
                    }
        }
    }
}