package com.omni.roominkotlinfirsttry.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "PetEntity")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null  ,
    var name:String ,
    var breed:String,
    var gender:String = "Male",
    var weight:Double=0.0
)