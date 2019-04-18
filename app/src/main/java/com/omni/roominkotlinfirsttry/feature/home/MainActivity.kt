package com.omni.roominkotlinfirsttry.feature.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.omni.entities.PetEntity
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.feature.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal  val petsViewModel: PetViewModel by lazy {
        ViewModelProviders.of(this).get(PetViewModel::class.java)
    }
    private lateinit var allpets: List<PetEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab?.setOnClickListener {
            Intent(this, DetailsActivity::class.java)
                    .also { startActivity(it) }
        }
        bindViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_catalog, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        when (id) {
            R.id.action_delete_all_entries -> deleteAll()
            R.id.action_insert_dummy_data -> insertDummyData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDummyData() {
        val pet = PetEntity(name = "dolcy", breed = "dog", weight = 44.0)
        petsViewModel.insert(pet)

    }

    private fun deleteAll() {
//        if (allpets.isNotEmpty()) {
//            petsViewModel.deleteAll(allpets)
////            adapter.removePets()
//        }

    }
}

private fun MainActivity.bindViews() = kotlin.with(petsViewModel) {

    kotlin.with(pets_list) {
        Log.d("dataPets" , petsResult.value.toString())
        layoutManager = android.support.v7.widget.LinearLayoutManager(this@bindViews, LinearLayoutManager.VERTICAL, false)
        adapter = com.omni.roominkotlinfirsttry.feature.home.PetsAdapter(this@bindViews, petsResult)
    }
//    emptyViewLiveData.observe(this@bindViews, Observer { isEmpty ->
//        if (isEmpty!!)
//            empty_view.visibility = View.VISIBLE
//        else
//            empty_view.visibility = View.GONE
//
//    })

}
