package com.omni.roominkotlinfirsttry

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.omni.roominkotlinfirsttry.data.PetEntity
import com.omni.roominkotlinfirsttry.data.PetViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var petsViewModel: PetViewModel
    private lateinit var gender: String
    private val currentIntent: Intent by lazy {
        intent
    }
    private lateinit var currentPetEntity: PetEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setUpSpinner()

        petsViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)


        edit_pet_name.setOnKeyListener { _, _, _ ->
            if (isValidEntry(edit_pet_name.text!!)) {
                // Clear the error.
                edit_pet_name.error = null
            }
            false
        }

        edit_pet_breed.setOnKeyListener { _, _, _ ->
            if (isValidEntry(edit_pet_breed.text!!)) {
                // Clear the error.
                edit_pet_breed.error = null
            }
            false
        }

        if (currentIntent.hasExtra("pet")) {
            currentPetEntity = currentIntent.getParcelableExtra("pet")
        }
    }

    private fun isValidEntry(text: Editable): Boolean {
        return text.toString().isNotEmpty()
    }


    private fun setUpSpinner() {
        val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_options,
                android.R.layout.simple_spinner_item)

        spinner_gender.adapter = spinnerAdapter
        spinner_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                gender = getString(R.string.unknown_breed)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gender = p0.toString()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editor, menu)
        if (!currentIntent.hasExtra("pet"))
            menu?.findItem(R.id.action_delete)?.isVisible = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item!!.itemId
        when (itemId) {
            R.id.action_save -> savePet()
            R.id.action_delete -> deletePet()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePet() {
        alertDialog(currentPetEntity)
    }

    private fun savePet() {
        val name = edit_pet_name.text.toString()
        val breed = edit_pet_breed.text.toString()
        val weight = edit_pet_weight.text.toString()

        if (name.isNotEmpty() && breed.isNotEmpty()) {
            val pet = PetEntity(name = name, breed = breed, weight = weight.toDouble(), gender = gender)
            petsViewModel.insert(pet)
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

    private fun alertDialog(petEntity: PetEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure ?")
        builder.setPositiveButton("Yes") { _, _ ->
            petsViewModel.delete(petEntity)
            Toast.makeText(this, "$petEntity.name is deleted", Toast.LENGTH_SHORT).show()

        }
        builder
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.dismiss()
                }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


}
