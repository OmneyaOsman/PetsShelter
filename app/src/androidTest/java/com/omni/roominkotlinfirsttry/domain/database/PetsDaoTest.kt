package com.omni.roominkotlinfirsttry.domain.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.omni.roominkotlinfirsttry.entities.PetEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PetDaoTest {

    // Executes each task synchronously using Architecture Components.
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var db: PetsDatabase
    private lateinit var petDao: PetsDao
    private val pet = PetEntity(name = "Lucy", breed = "breed", gender = "Female", weight = 30.0)

    @Before
    fun setUpLocalDataSource() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PetsDatabase::class.java).build()
        petDao = db.petsDao

    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertNewPetSavesData() = runBlockingTest {
        //arrange
        // Act
        petDao.insert(pet)
        advanceTimeBy(1_000)
        val petFromDB: PetEntity? = petDao.findPetByName(pet.name)

        //assert
        MatcherAssert.assertThat(pet, IsEqual(petFromDB))
    }

}