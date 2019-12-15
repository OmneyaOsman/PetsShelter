package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.entities.PetEntity


class MainActivity : AppCompatActivity() {

    private val petsViewModel: PetViewModel by lazy {
        ViewModelProviders.of(this).get(PetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_catalog, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId.let {
            when (it) {
                R.id.action_delete_all_entries -> petsViewModel.deleteAll()
                R.id.action_insert_dummy_data -> insertDummyData()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDummyData() {
        val pet = PetEntity(name = "dolcy", breed = "dog", weight = 44.0)
        petsViewModel.savePet(pet)

    }
}



