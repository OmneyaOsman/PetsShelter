package com.omni.roominkotlinfirsttry

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.omni.roominkotlinfirsttry.adapter.PetsAdapter
import com.omni.roominkotlinfirsttry.data.PetEntity
import com.omni.roominkotlinfirsttry.data.PetViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var petsViewModel: PetViewModel
    private lateinit var allpets: List<PetEntity>
    private lateinit var adapter: PetsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab?.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        petsViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)

        setUpRecyclerView()

        val simpleItemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

                petsViewModel.delete(viewHolder!!.itemView.tag as PetEntity)
            }

        }

        ItemTouchHelper(simpleItemTouchHelper).attachToRecyclerView(pets_list!!)
    }

    //todo read https://www.androidhive.info/2017/09/android-recyclerview-swipe-delete-undo-using-itemtouchhelper/


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
        if ( allpets.isNotEmpty()) {
            petsViewModel.deleteAll(allpets)
            adapter.removePets()
        }

    }


    private fun setUpRecyclerView() {
        pets_list?.layoutManager = LinearLayoutManager(this)
        adapter = PetsAdapter(this)
        pets_list?.adapter = adapter
        petsViewModel.allpets.observe(this, Observer<List<PetEntity>> { t ->
            if (t != null && t.isNotEmpty()) {
                empty_view.visibility = View.GONE
                allpets = t
                adapter.setPets(allpets)
            } else
                empty_view.visibility = View.VISIBLE


        })
    }
}
