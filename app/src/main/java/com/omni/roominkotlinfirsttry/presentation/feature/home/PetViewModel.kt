package com.omni.roominkotlinfirsttry.presentation.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omni.roominkotlinfirsttry.domain.ViewState
import com.omni.roominkotlinfirsttry.domain.engine.toMutableLiveData
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.entities.PetEntity

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
        viewState.postValue(ViewState(loading = true))
//        petsRepository.getAllPets()
//                .also { emptyViewLiveData.postValue(true) }
//                .takeUnless { it.isNullOrEmpty()}
//                ?.also { emptyViewLiveData.postValue(false) }
//                ?.also { result.postValue(it) } ?: result.postValue(ArrayList())

    }

    fun insertNewPet(petEntity: PetEntity) {
//        petEntity.takeUnless { it.name.isBlank() }
//                ?.also { emptyViewLiveData.postValue(false) }
//                ?.let { petsRepository.insertAPet(it) }
//                ?.also { result.add(petEntity) }
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