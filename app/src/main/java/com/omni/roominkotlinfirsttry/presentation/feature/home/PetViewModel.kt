package com.omni.roominkotlinfirsttry.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.entities.PetEntity
import com.omni.roominkotlinfirsttry.entities.ViewState
import kotlinx.coroutines.launch

typealias PetsResult = LiveData<PagedList<PetEntity>>

class PetViewModel(
        val viewState: MutableLiveData<ViewState> = MutableLiveData(),
        private val petsRepository: PetsDataSource = repository
) : ViewModel() {

    private var _payload: PetsResult = petsRepository.loadPets()
    val payload: PetsResult = _payload


    init {
        updateViewState(ViewState(loading = true))
    }

    private fun updateViewState(state: ViewState) {
        viewState.postValue(state)
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