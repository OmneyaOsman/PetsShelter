package com.omni.domain.engine

import androidx.lifecycle.MutableLiveData

fun <T> T.toMutableLiveData(): MutableLiveData<T> {
    return MutableLiveData<T>()
            .also { it.postValue( this) }
}


fun <T> MutableLiveData<List<T>>.add(item: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.add(item)
    this.postValue(updatedItems)
}

