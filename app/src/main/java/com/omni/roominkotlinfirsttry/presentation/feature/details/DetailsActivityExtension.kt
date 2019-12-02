package com.omni.roominkotlinfirsttry.presentation.feature.details

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.omni.roominkotlinfirsttry.entities.PetEntity
import com.omni.roominkotlinfirsttry.R
import kotlinx.android.synthetic.main.activity_details.*

fun DetailsActivity.bindViews() {
    with(spinner_gender) {
        adapter = ArrayAdapter.createFromResource(this@bindViews, R.array.array_gender_options,
                android.R.layout.simple_spinner_item)

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                gender = getString(R.string.unknown_breed)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gender = p0.toString()
            }

        }
    }
}

fun DetailsActivity.saveAPet() {
    val name = edit_pet_name.text.toString()
    val breed = edit_pet_breed.text.toString()
    val weight = edit_pet_weight.text.toString()

    if (name.isNotEmpty() && breed.isNotEmpty()) {
        val pet = PetEntity(name = name, breed = breed, weight = weight.toDouble(), gender = gender)
        viewModel.insertNewPet(pet)
        finish()
    } else {
        if (name.isEmpty())
            edit_pet_name.error = "Required"
        else
            edit_pet_name.error = null

        if (breed.isEmpty())
            edit_pet_breed.error = "Required"
        else
            edit_pet_breed.error = null


    }

}

