package com.omni.domain.usescase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.omni.domain.repositories.PetsDataSource
import com.omni.entities.PetEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

//  pattern: [methodName]With[Input]Then[ExpectedResult]()
class RetrieveAllPetsUseCaseTest {

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var petsDataSource: PetsDataSource


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `invoke with successful response then update resultLiveData value`() {
        //Arrange
        val resultLiveData = MutableLiveData<List<PetEntity>>()
        val repository = mock<PetsDataSource>()
        val emptyViewLiveData = MutableLiveData<Boolean>()
        val retrieveAllPetsUseCase = RetrieveAllPetsUseCase(resultLiveData , emptyViewLiveData,repository)
        //Act
        whenever(repository.getAllPets())
                .thenReturn(emptyList())

        //Assert

// Assert
//        Assert.assertTrue(result.value!!.isNotEmpty())
        }


//    // Given that the UserDataSource returns a user
//    User user = new User("user name");
//    when(mDataSource.getUser()).thenReturn(Flowable.just(user));
//
//    //When getting the user name
//    mViewModel.getUserName()
//    .test()
//    // The correct user name is emitted
//    .assertValue("user name");

}