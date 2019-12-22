package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.databinding.PetsHomeFragmentBinding
import com.omni.roominkotlinfirsttry.entities.PetEntity

class PetsHomeFragment : Fragment() {

    private val viewModel by viewModels<PetsListViewModel>()
    private lateinit var binding: PetsHomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PetsHomeFragmentBinding.inflate(inflater, container, false)
                .apply {
                    petsViewModel = viewModel
                    lifecycleOwner = this@PetsHomeFragment
                }
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPetsRecyclerView()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_petsHomeFragment_to_addEditPetFragment)
        }

    }


    private fun setUpPetsRecyclerView() {
        with(binding.petsList) {
            adapter = PetsAdapter()
            addItemDecoration(DividerItemDecoration(requireContext(),
                    DividerItemDecoration.VERTICAL))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_catalog, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all_entries -> {
                viewModel.deleteAll()
                true
            }
            R.id.action_insert_dummy_data -> {
                insertDummyData()
                true
            }
            else -> false
        }
    }


    private fun insertDummyData() {
        val pet = PetEntity(name = "dolcy", breed = "dog", weight = 44.0)
        viewModel.savePet(pet)

    }

}