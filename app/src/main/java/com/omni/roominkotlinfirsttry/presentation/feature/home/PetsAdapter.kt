package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omni.roominkotlinfirsttry.databinding.PetListItemBinding
import com.omni.roominkotlinfirsttry.entities.PetEntity


class PetsAdapter : PagedListAdapter<PetEntity, PetsAdapter.PetsViewHolder>(PET_COMPARATOR) {

    class PetsViewHolder private constructor(private val binding: PetListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.pet?.let { pet ->
                    navigateToPetDetails(pet, it)
                }
            }

        }

        private fun navigateToPetDetails(pet: PetEntity, view: View) {
            view.findNavController()
                    .navigate(PetsHomeFragmentDirections
                            .actionPetsHomeFragmentToPetDetailsFragment(pet.id))
        }

        fun bind(petEntity: PetEntity) {
            binding.pet = petEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PetsViewHolder {
                return LayoutInflater.from(parent.context).let {
                    PetListItemBinding.inflate(it, parent, false)
                }.let {
                    PetsViewHolder(it)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PetsViewHolder.from(parent)


    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        val PET_COMPARATOR = object : DiffUtil.ItemCallback<PetEntity>() {
            override fun areItemsTheSame(oldItem: PetEntity, newItem: PetEntity): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PetEntity, newItem: PetEntity): Boolean =
                    oldItem == newItem

        }
    }
}
