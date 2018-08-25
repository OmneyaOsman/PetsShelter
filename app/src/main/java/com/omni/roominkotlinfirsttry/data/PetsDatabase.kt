package com.omni.roominkotlinfirsttry.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(PetEntity::class)], version = 1)
abstract class PetsDatabase : RoomDatabase() {
    abstract fun petsDao(): PetsDao

    companion object {
        var database: PetsDatabase? = null
        @Synchronized
        fun getInstance(context: Context): PetsDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context.applicationContext, PetsDatabase::class.java, "Pets-Database")
                        .fallbackToDestructiveMigration()
                        .build()

            }
            return database!!
        }
    }


}