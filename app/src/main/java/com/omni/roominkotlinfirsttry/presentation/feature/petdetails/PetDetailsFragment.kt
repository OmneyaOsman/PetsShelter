package com.omni.roominkotlinfirsttry.presentation.feature.petdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.omni.roominkotlinfirsttry.databinding.PetDetailsFragmentBinding

class PetDetailsFragment : Fragment() {

    private lateinit var binding: PetDetailsFragmentBinding
    private val args: PetDetailsFragmentArgs by navArgs()
    private val viewModel: PetDetailsViewModel by viewModels {
        PetDetailsViewModelFactory(args.petId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PetDetailsFragmentBinding.inflate(inflater, container, false)
                .apply {
                    petViewModel = viewModel
                    lifecycleOwner = this@PetDetailsFragment
                }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}