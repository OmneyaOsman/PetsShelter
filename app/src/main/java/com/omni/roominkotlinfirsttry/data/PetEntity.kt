package com.omni.roominkotlinfirsttry.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "PetEntity")
data class PetEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var name: String,
        var breed: String,
        var gender: String = "Male",
        var weight: Double = 0.0
) : Parcelable