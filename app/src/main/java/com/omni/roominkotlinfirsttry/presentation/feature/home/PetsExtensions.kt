package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.omni.roominkotlinfirsttry.entities.PetEntity

@BindingAdapter("app:payload" ,"app:attachedEmptyView")
fun RecyclerView.setItems(items: PagedList<PetEntity>?, emptyView: View) {
    items?.also {
        emptyView.visibility = if (it.size > 0) View.GONE else View.VISIBLE
    }.let {
        (adapter as PetsAdapter).submitList(items)
    }
}