package com.example.android.architecture.blueprints.todoapp

import com.omni.roominkotlinfirsttry.entities.PetEntity

object PetsFactory {

    val TEST_PET= PetEntity(1,name = "Lucy", breed = "breed", gender = "Female", weight = 30.0)
    val TEST_PET_ONE= PetEntity(2,name = "peet", breed = "breed", gender = "Female", weight = 30.0)
    val TEST_PET_TWO= PetEntity(3,name = "mark", breed = "breed", gender = "Female", weight = 30.0)
    fun createList () = listOf(TEST_PET , TEST_PET_ONE , TEST_PET_TWO)

}