package com.omni.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
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