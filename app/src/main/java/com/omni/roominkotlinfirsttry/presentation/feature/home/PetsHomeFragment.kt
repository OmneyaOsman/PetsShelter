package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.omni.roominkotlinfirsttry.databinding.PetsHomeFragmentBinding

class PetsHomeFragment : Fragment() {

    private val viewModel by viewModels<PetViewModel>()
    private lateinit var binding: PetsHomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PetsHomeFragmentBinding.inflate(inflater, container, false)
                .apply {
                    petsViewModel = viewModel
                    lifecycleOwner = this@PetsHomeFragment
                }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPetsRecyclerView()
    }

    private fun setUpPetsRecyclerView() {
        with(binding.petsList) {
            this.adapter = PetsAdapter()
            this.addItemDecoration(DividerItemDecoration(requireContext(),
                    DividerItemDecoration.VERTICAL))
        }
    }

}