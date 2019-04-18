package com.omni.domain

import android.app.Application
import android.arch.lifecycle.MutableLiveData


// liveData here as channel to access application
// internal for module
internal val applicationLiveData = MutableLiveData<Application>()

internal fun MutableLiveData<Application>.getApplication():Application =value!!

object Domain {

    fun integrateWith(application: Application) {

        applicationLiveData.value = application
    }

}