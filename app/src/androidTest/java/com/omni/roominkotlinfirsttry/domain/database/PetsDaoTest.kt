package com.omni.roominkotlinfirsttry.domain.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.PetsFactory
import com.omni.roominkotlinfirsttry.entities.PetEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PetsDaoTest {

    // Executes each task synchronously using Architecture Components.
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var db: PetsDatabase
    private lateinit var petDao: PetsDao

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
    @Throws(Exception::class)
    fun insertNewPetSavesData() = runBlocking {
        //arrange
        val pet = PetsFactory.TEST_PET

        // Act
        petDao.insert(pet)
        val petFromDB: PetEntity? = petDao.findPetByName(pet.name)

        //assert
        assertThat(petFromDB?.id, IsEqual(1))
        assertThat(pet, IsEqual(petFromDB))
    }

    @Test
    @Throws(Exception::class)
    fun insertPetThenDeletePetByIdDeletesReturnsNull() = runBlocking {
        //arrange
        val pet = PetsFactory.TEST_PET_ONE

        // Act
        petDao.insert(pet)
        petDao.deletePetById(pet.id)
        val retrievedPet = petDao.getPetById(pet.id)

        assertThat(retrievedPet , `is`(nullValue()))
    }

    @Test
    fun whenInsertPetsThenReturnsListOfPets()= runBlocking {
        //Arrange
        val petsList =PetsFactory.createPetsList()
        petsList.forEach {
            petDao.insert(it)
        }
        //Act
        val retrievedList = petDao.getAllPets()

        //Assert
        assertThat(retrievedList ,`is`(notNullValue()) )
        assertThat(retrievedList ,not(emptyList()) )
        assertThat(retrievedList , hasSize(petsList.size) )
        assertThat(retrievedList , IsEqual(petsList) )
    }


    @Test
    fun insertListOfPetsThenDeleteAllReturnsEmptyList() = runBlocking {
        //Arrange
        val petsList =PetsFactory.createPetsList()
        petsList.forEach {
            petDao.insert(it)
        }
        //Act
        petDao.deleteAll(petsList)
        val retrievedList = petDao.getAllPets()

        //Assert
        assert(retrievedList.isEmpty() )
    }

}