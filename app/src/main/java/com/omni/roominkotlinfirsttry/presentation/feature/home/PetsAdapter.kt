package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.entities.PetEntity
import kotlinx.android.synthetic.main.list_item.view.*

//TODO add onClick listener to pass pet and work on delete and update PetEntity

class PetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(petEntity: PetEntity) {
        itemView.name_item_list.text = petEntity.name
        val summary = "Breed is : ${petEntity.breed} and weight is:${petEntity.weight}"
        itemView.summary_item_list.text = summary
    }
}

class PetsAdapter(lifecycleOwner: LifecycleOwner,
                  private val petsResult: MutableLiveData<List<PetEntity>>)
    : RecyclerView.Adapter<PetsViewHolder>() {


    init {
        petsResult.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
                .let { PetsViewHolder(it) }
    }

    override fun getItemCount(): Int {
        return petsResult.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val petEntity = petsResult.value!![position]
        holder.bindView(petEntity)


    }


//    fun setPets(petsList: List<PetEntity>) {
//        list = petsList.toMutableList()
//        notifyDataSetChanged()
//    }
//
//    fun removePets() {
//        list.clear()
//        notifyDataSetChanged()
//    }


}