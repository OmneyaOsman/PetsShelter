package com.omni.roominkotlinfirsttry.presentation.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omni.roominkotlinfirsttry.domain.ViewState
import com.omni.roominkotlinfirsttry.domain.engine.toMutableLiveData
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.entities.PetEntity
import kotlinx.coroutines.launch

typealias PetsResult = MutableLiveData<List<PetEntity>>

class PetViewModel(
        val viewState: MutableLiveData<ViewState> = MutableLiveData(),
        val petsResult: PetsResult = ArrayList<PetEntity>().toMutableLiveData(),
        private val petsRepository: PetsDataSource = repository
) : ViewModel() {

    init {
        retrievePets()
    }

    private fun retrievePets() {
//        viewState.value = viewState.value?.copy(loading = true)
//        viewState.postValue(ViewState(loading = true))
//        petsRepository.getAllPets()
//                .also { emptyViewLiveData.postValue(true) }
//                .takeUnless { it.isNullOrEmpty()}
//                ?.also { emptyViewLiveData.postValue(false) }
//                ?.also { result.postValue(it) } ?: result.postValue(ArrayList())

    }

    fun savePet(petEntity: PetEntity) {
        petEntity.takeUnless { it.name.isBlank() }
                ?.let {
                    viewModelScope.launch {
                        petsRepository.insertAPet(it)
                    }
                }
                ?.also { viewState.postValue(ViewState(success = true)) }
    }


    fun delete(petEntity: PetEntity) {
//        petEntity.takeUnless { it.name.isBlank() }
//                ?.let { petsRepository.deleteAPet(it)}
//                ?.let { result.remove(petEntity) }
//
//        result.takeIf {it.value?.isNullOrEmpty() !!}
//                .also { emptyViewLiveData.postValue(true) }
    }

    fun deleteAll() {
//        petsRepository.getAllPets()
//                .takeUnless { it.isEmpty()}
//                ?.let { petsRepository.deleteAllPets(it) }
//                ?.also { emptyViewLiveData.postValue(true) }
//                ?.also { result.postValue(ArrayList()) }
    }


    override fun onCleared() {
        super.onCleared()
    }

}