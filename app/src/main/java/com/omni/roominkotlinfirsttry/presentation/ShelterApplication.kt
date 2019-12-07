package com.omni.roominkotlinfirsttry.presentation

import android.app.Application
import com.omni.roominkotlinfirsttry.BuildConfig
import com.omni.roominkotlinfirsttry.domain.Domain
import timber.log.Timber

class ShelterApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Domain.integrateWith(this)
    }
}