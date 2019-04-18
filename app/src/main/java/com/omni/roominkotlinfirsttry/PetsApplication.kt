package com.omni.roominkotlinfirsttry

import android.app.Application
import com.omni.domain.Domain

class PetsApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        Domain.integrateWith(this)
    }
}