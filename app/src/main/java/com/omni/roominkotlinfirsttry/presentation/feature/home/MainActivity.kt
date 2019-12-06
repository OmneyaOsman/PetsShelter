package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.entities.PetEntity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val petsViewModel: PetViewModel by lazy {
        ViewModelProviders.of(this).get(PetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        bindViews()

        fab?.setOnClickListener {
            Toast.makeText(this, "under work", Toast.LENGTH_LONG)
                    .show()
//            Intent(this, DetailsActivity::class.java)
//                    .also { startActivity(it) }
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
        petsViewModel.savePet(pet)
        petsViewModel.viewState.observe(this, Observer {
            if (it == null)
                Timber.tag("Main Insert").d(String.format("failed", "failed"))
            else
                Timber.tag("Main Insert").d(String.format("Success", it.success.toString()))

        })
    }
}



