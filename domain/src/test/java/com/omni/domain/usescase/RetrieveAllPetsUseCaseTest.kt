package com.omni.domain.usescase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.omni.domain.repositories.PetsRepository
import com.omni.entities.PetEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

//  pattern: [methodName]With[Input]Then[ExpectedResult]()
class RetrieveAllPetsUseCaseTest {

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `invoke with successful response then update resultLiveData value`() {
        //Arrange
        val resultLiveData = MutableLiveData<List<PetEntity>>()
        val repository = mock<PetsRepository>()
        val emptyViewLiveData = MutableLiveData<Boolean>()
        val retrieveAllPetsUseCase = RetrieveAllPetsUseCase(resultLiveData , emptyViewLiveData,repository)
        //Act

        //Assert

    }


}