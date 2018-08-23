package com.omni.roominkotlinfirsttry.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.data.PetEntity
import kotlinx.android.synthetic.main.list_item.view.*

 class PetsAdapter(context: Context) : RecyclerView.Adapter<PetsAdapter.PetsViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var list: List<PetEntity>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        val view = layoutInflater.inflate(R.layout.list_item , parent , false)
        return PetsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (list != null)
            list!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        if(list!=null){
            val petEntity = list!![position]
            holder.nameView.text= petEntity.name
            val summary ="Breed is : ${petEntity.breed} and weight is:${petEntity.weight}"
            holder.summaryView.text = summary
        }

    }


     fun setPets(petsList: List<PetEntity>) {
        this.list = petsList
        notifyDataSetChanged()
    }

   inner class PetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.name!!
        val summaryView = itemView.summary!!
    }
}