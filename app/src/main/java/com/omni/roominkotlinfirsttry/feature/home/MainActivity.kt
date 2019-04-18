package com.omni.roominkotlinfirsttry.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.omni.entities.PetEntity
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.feature.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal val petsViewModel: PetViewModel by lazy {
        ViewModelProviders.of(this).get(PetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()

        fab?.setOnClickListener {
            Intent(this, DetailsActivity::class.java)
                    .also { startActivity(it) }
        }

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
        petsViewModel.insertNewPet(pet)
    }
}



