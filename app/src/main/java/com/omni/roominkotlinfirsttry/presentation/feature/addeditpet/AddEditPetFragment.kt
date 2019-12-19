package com.omni.roominkotlinfirsttry.presentation.feature.addeditpet

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omni.roominkotlinfirsttry.R
import com.omni.roominkotlinfirsttry.databinding.AddEditTaskFragmentBinding
import com.omni.roominkotlinfirsttry.entities.PetEntity

class AddEditPetFragment : Fragment() {

    private val viewModel by viewModels<PetViewModel>()
    private lateinit var binding: AddEditTaskFragmentBinding
    private lateinit var gender: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddEditTaskFragmentBinding.inflate(inflater, container, false)
                .apply {
                    petViewModel = viewModel
                    lifecycleOwner = this@AddEditPetFragment
                }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpGenderSpinner()
        with(binding.editPetName) {
            setOnKeyListener { _, _, _ ->
                if (isValidEntry()) error = null
                false
            }
        }
        with(binding.editPetBreed) {
            this.setOnKeyListener { _, _, _ ->
                if (isValidEntry()) error = null
                false
            }
        }
    }

    private fun setUpGenderSpinner() {
        with(binding.spinnerGender) {
            adapter = ArrayAdapter.createFromResource(requireContext(), R.array.array_gender_options,
                    android.R.layout.simple_spinner_item)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    gender = getString(R.string.unknown_breed)
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    gender = p0.toString()
                }

            }
        }
    }

    private fun saveAPet() {
        val name = binding.editPetName.text.toString()
        val breed = binding.editPetBreed.text.toString()
        val weight = binding.editPetWeight.text.toString()

        if (name.isNotEmpty() && breed.isNotEmpty()) {
            val pet = PetEntity(name = name, breed = breed, weight = weight.toDouble(), gender = gender)
            viewModel.savePet(pet)
            findNavController().navigateUp()
        } else {
            if (name.isEmpty())
                binding.editPetName.error = "Required"
            else
                binding.editPetName.error = null

            if (breed.isEmpty())
                binding.editPetBreed.error = "Required"
            else
                binding.editPetBreed.error = null
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_editor, menu)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        if (!currentIntent.hasExtra("pet"))
//            menu?.findItem(R.id.action_delete)?.isVisible = false
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveAPet()
                true
            }
            R.id.action_delete -> {
                true
            }
            else -> false
        }
    }
}