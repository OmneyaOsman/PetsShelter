package com.omni.roominkotlinfirsttry.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Pet")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0 ,
    var name:String ="",
    var breed:String="",
    var weight:Double=0.0
)