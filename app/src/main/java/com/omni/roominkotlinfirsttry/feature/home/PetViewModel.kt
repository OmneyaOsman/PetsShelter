package com.omni.roominkotlinfirsttry.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omni.domain.engine.toMutableLiveData
import com.omni.domain.repositories.PetsRepository
import com.omni.domain.repositories.repository
import com.omni.domain.usescase.InsertingNewPetUseCase
import com.omni.domain.usescase.PetsResult
import com.omni.domain.usescase.RetrieveAllPetsUseCase
import com.omni.entities.PetEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PetViewModel(
        private val petsRepository: PetsRepository = repository,
        val emptyViewLiveData: MutableLiveData<Boolean> = true.toMutableLiveData(),
        val petsResult: PetsResult = ArrayList<PetEntity>().toMutableLiveData(),
        private val disposables: CompositeDisposable = CompositeDisposable(),
        private val retrieveAllPetsUseCase: RetrieveAllPetsUseCase =
                RetrieveAllPetsUseCase(petsResult, emptyViewLiveData),
        private val insertingNewPetUseCase: InsertingNewPetUseCase =
                InsertingNewPetUseCase(petsResult, emptyViewLiveData)

) : ViewModel() {



    init {

        retrievePets()
    }

    private fun retrievePets() {
        Observable.fromCallable { retrieveAllPetsUseCase() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()
                .also { disposables.add(it) }
    }


    fun insertNewPet(petEntity: PetEntity): Disposable =
            Observable.fromCallable { insertingNewPetUseCase(petEntity) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                    .also { disposables.add(it) }

//
//    fun delete(petEntity: PetEntity) {
//        petsRepository.deleteAPet(petEntity)
//    }

    fun deleteAll() {
        Observable.fromCallable { petsRepository.deleteAllPets(petsResult.value!!) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .also { disposables.add(it) }

    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}