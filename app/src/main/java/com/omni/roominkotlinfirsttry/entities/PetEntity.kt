package com.omni.roominkotlinfirsttry.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PetEntity")
data class PetEntity @JvmOverloads constructor(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "entryid") var id: Int? = null,
        var name: String = "",
        var breed: String = "",
        var gender: String = "Male",
        var weight: Double = 0.0
)