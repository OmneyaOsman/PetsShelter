package com.omni.roominkotlinfirsttry.presentation.feature.petdetails

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.domain.repositories.PetsDataSource
import com.omni.roominkotlinfirsttry.domain.repositories.repository
import com.omni.roominkotlinfirsttry.entities.PetEntity
import com.omni.roominkotlinfirsttry.entities.Result
import kotlinx.coroutines.launch


class PetDetailsViewModel(private val _petId: MutableLiveData<Int> = MutableLiveData(),
                          private val petsRepository: PetsDataSource = repository) : ViewModel() {


    private val _petDetails = _petId.switchMap {
        petsRepository.observePet(it).map { result ->
            getResult(result)
        }
    }

    val petDetails = _petDetails

    private fun getResult(taskResult: Result<PetEntity>): PetEntity? {
        return if (taskResult is Result.Success) {
            taskResult.data
        } else {
            showSnackbarMessage(R.string.loading_pet_error)
            null
        }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun update(petEntity: PetEntity) {
        petEntity.takeUnless { it.name.isBlank() }
                ?.let {
                    viewModelScope.launch {
                        petsRepository.updateAPet(it)
                    }
                }
    }


    fun delete() =
        viewModelScope.launch {
            _petId.value?.let {
                petsRepository.deleteAPetById(it)
            }
        }
}