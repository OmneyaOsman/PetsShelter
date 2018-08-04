package com.omni.roominkotlinfirsttry.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Pet::class) ,version = 1)
abstract class  PetsDatabase :RoomDatabase() {

    abstract fun petsDao() :PetsDao
}