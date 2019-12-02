package com.omni.roominkotlinfirsttry.presentation.feature.details

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.omni.roominkotlinfirsttry.entities.PetEntity
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.presentation.feature.home.PetViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    internal val viewModel: PetViewModel by lazy {
                ViewModelProviders.of(this).get(PetViewModel::class.java)

    }
    lateinit var gender: String
    private val currentIntent: Intent by lazy {
        intent
    }
    private lateinit var currentPetEntity: PetEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        bindViews()

        //TODO create DetailsViewModel


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editor, menu)
        if (!currentIntent.hasExtra("pet"))
            menu?.findItem(R.id.action_delete)?.isVisible = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item!!.itemId
        when (itemId) {
            R.id.action_save -> saveAPet()
            R.id.action_delete -> deletePet()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePet() {
        alertDialog(currentPetEntity)
    }


    private fun alertDialog(petEntity: PetEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure ?")
        builder.setPositiveButton("Yes") { _, _ ->
            //            petsViewModel.delete(petEntity)
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
