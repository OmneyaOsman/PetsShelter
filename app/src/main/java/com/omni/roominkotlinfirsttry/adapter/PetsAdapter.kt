package com.omni.roominkotlinfirsttry.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.data.PetEntity
import kotlinx.android.synthetic.main.list_item.view.*

//TODO add onClick listener to pass pet and work on delete and update PetEntity
 class PetsAdapter(context: Context) : RecyclerView.Adapter<PetsAdapter.PetsViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var list = mutableListOf<PetEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        val view = layoutInflater.inflate(R.layout.list_item , parent , false)
        return PetsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
            val petEntity = list[position]
            holder.itemView.tag = petEntity
            holder.nameView.text= petEntity.name
            val summary ="Breed is : ${petEntity.breed} and weight is:${petEntity.weight}"
            holder.summaryView.text = summary

    }


     fun setPets(petsList: List<PetEntity>) {
        list = petsList.toMutableList()
        notifyDataSetChanged()
    }
     fun removePets() {
         list.clear()
        notifyDataSetChanged()
    }

   inner class PetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.name!!
        val summaryView = itemView.summary!!
    }
}