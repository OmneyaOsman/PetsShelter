package com.omni.roominkotlinfirsttry

import android.app.Application
import android.arch.persistence.room.Room
import com.omni.roominkotlinfirsttry.data.PetsDatabase

class AppController:Application() {

    companion object {
        var database:PetsDatabase?=null
    }

    /**The fallbackToDestructiveMigration says that if there is no migration class provided with an incrementation of the db version,
     *  it will drop any table and recreate the database.
     *  It allows to avoid the crash while incrementing the database version without migration strategy
     *  (otherwise than be ok to loose all the data)*/
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this ,PetsDatabase::class.java ,"Pets-Database")
                .fallbackToDestructiveMigration()
                .build()
    }

}