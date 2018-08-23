package com.omni.roominkotlinfirsttry

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.omni.roominkotlinfirsttry.adapter.PetsAdapter
import com.omni.roominkotlinfirsttry.data.PetEntity
import com.omni.roominkotlinfirsttry.data.PetViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var petsViewModel: PetViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
        petsViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        pets_list?.layoutManager = LinearLayoutManager(this)
        val adapter = PetsAdapter(this)
        pets_list?.adapter = adapter
        petsViewModel?.allpets?.observe(this, Observer<List<PetEntity>> { t ->
            if (t != null && t.isNotEmpty()) {
                empty_view.visibility = View.GONE
                adapter.setPets(t)
            } else
                empty_view.visibility = View.VISIBLE


        })
    }
}
