package com.omni.roominkotlinfirsttry.domain.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omni.roominkotlinfirsttry.domain.applicationLiveData
import com.omni.roominkotlinfirsttry.domain.getApplication


private const val DATABASE_NAME = "Pets_database"

val petsDatabase: PetsDatabase by lazy {
    buildDatabase(applicationLiveData.getApplication())
}

private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context, PetsDatabase::class.java
                , DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


@Database(entities = [(com.omni.roominkotlinfirsttry.entities.PetEntity::class)], version = 1, exportSchema = false)
abstract class PetsDatabase : RoomDatabase() {
    abstract val petsDao: PetsDao

}