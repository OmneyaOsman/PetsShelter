package com.omni.domain.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.omni.entities.PetEntity

@Database(entities = [(PetEntity::class)], version = 1 ,exportSchema = false)
abstract class PetsDatabase : RoomDatabase() {
    abstract fun petsDao(): PetsDao

    companion object {
        @Volatile
        private var INSTANCE: PetsDatabase? = null

        fun getDatabase(context: Context): PetsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PetsDatabase::class.java,
                        "Pets_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}