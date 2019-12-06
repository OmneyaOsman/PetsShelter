package com.omni.roominkotlinfirsttry.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "PetEntity")
data class PetEntity @JvmOverloads constructor(
        @PrimaryKey @ColumnInfo(name = "entryid") var id: String =  UUID.randomUUID().toString(),
        var name: String = "",
        var breed: String = "",
        var gender: String = "Male",
        var weight: Double = 0.0
)