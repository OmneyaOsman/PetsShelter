package com.omni.roominkotlinfirsttry.feature.home

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

fun MainActivity.bindViews() = with(petsViewModel) {

    with(pets_list) {
        Log.d("dataPets", petsResult.value.toString())
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@bindViews)
        adapter = PetsAdapter(this@bindViews, petsResult)
    }
    emptyViewLiveData.observe(this@bindViews, Observer { isEmpty ->
        Log.d("dataP" , "$isEmpty")
        if (!isEmpty)
            empty_view.visibility = View.GONE
        else
            empty_view.visibility = View.VISIBLE

    })
}