package com.omni.roominkotlinfirsttry.domain.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.omni.roominkotlinfirsttry.domain.database.PetsDatabase
import com.omni.roominkotlinfirsttry.domain.database.petsDatabase
import com.omni.roominkotlinfirsttry.entities.PetEntity
import com.omni.roominkotlinfirsttry.entities.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val repository by lazy { LocalPetsDataSource() }


class LocalPetsDataSource(private val database: PetsDatabase = petsDatabase,
                          private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : PetsDataSource {

    override fun observePets(): LiveData<Result<List<PetEntity>>> =
            database.petsDao.observeAllPets().map {
                Result.Success(it)
            }

    override fun observePet(petId: Int): LiveData<Result<PetEntity>> =
            database.petsDao.observePetById(petId).map {
                Result.Success(it)
            }

    override suspend fun getAllPets(): Result<List<PetEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(database.petsDao.getAllPets())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun loadPets(): LiveData<PagedList<PetEntity>> =
            database.petsDao.retrievePets()
                    .toLiveData(Config(pageSize = 30))


    override suspend fun getPetById(petId: Int): Result<PetEntity> = withContext(ioDispatcher) {
        try {
            val pet = database.petsDao.getPetById(petId)
            if (pet != null) {
                return@withContext Result.Success(pet)
            } else
                return@withContext Result.Error(Exception("Pet Not Found"))
        } catch (ex: Exception) {
            return@withContext Result.Error(ex)
        }
    }


    override suspend fun insertAPet(pet: PetEntity) = withContext(ioDispatcher) {
        database.petsDao.insert(pet)
    }

    override suspend fun updateAPet(pet: PetEntity) =
            withContext<Unit>(ioDispatcher) {
                database.petsDao.update(pet)
            }

    override suspend fun deleteAPetById(petId: Int) = withContext<Unit>(ioDispatcher) {
        database.petsDao.deletePetById(petId)
    }

    override suspend fun deleteAllPets(pets: List<PetEntity>) = withContext(ioDispatcher) {
        database.petsDao.deleteAll(pets)
    }


}