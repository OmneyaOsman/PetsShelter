package com.omni.roominkotlinfirsttry.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omni.domain.engine.toMutableLiveData
import com.omni.domain.usescase.*
import com.omni.entities.PetEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PetViewModel(
        val emptyViewLiveData: MutableLiveData<Boolean> = true.toMutableLiveData(),
        val petsResult: PetsResult = ArrayList<PetEntity>().toMutableLiveData(),
        private val disposables: CompositeDisposable = CompositeDisposable(),
        private val retrieveAllPetsUseCase: RetrieveAllPetsUseCase =
                RetrieveAllPetsUseCase(petsResult, emptyViewLiveData),
        private val insertingNewPetUseCase: InsertingNewPetUseCase =
                InsertingNewPetUseCase(petsResult, emptyViewLiveData),
        private val deleteAllPetsUseCase: DeleteAllPetsUseCase =
                DeleteAllPetsUseCase(petsResult, emptyViewLiveData),
        private val deleteOnePetUseCase: DeleteAPetUseCase =
                DeleteAPetUseCase(petsResult, emptyViewLiveData)

) : ViewModel() {

    init {

        retrievePets()
    }

    private fun retrievePets() {
        Observable.fromCallable { retrieveAllPetsUseCase() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .also { disposables.add(it) }
    }


    fun insertNewPet(petEntity: PetEntity): Disposable =
            Observable.fromCallable { insertingNewPetUseCase(petEntity) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                    .also { disposables.add(it) }


    fun delete(petEntity: PetEntity) {
        Observable.fromCallable { deleteOnePetUseCase(petEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .also { disposables.add(it) }
    }

    fun deleteAll() {
        Observable.fromCallable { deleteAllPetsUseCase() }
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