package com.omni.roominkotlinfirsttry.presentation.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.omni.roominkotlinfirsttry.databinding.PetsHomeFragmentBinding
import timber.log.Timber

class PetsHomeFragment : Fragment() {

    private val viewModel by viewModels<PetViewModel>()
    private lateinit var binding :PetsHomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View {
        binding = PetsHomeFragmentBinding.inflate(inflater, container, false)
                .apply {
                    petsViewModel = viewModel
                    lifecycleOwner = this@PetsHomeFragment
                }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val adapter = PetsAdapter()

        with(binding.petsList){
            this.adapter = adapter
            this.addItemDecoration(decoration)
        }

        viewModel.payload.observe(this , Observer{
            Timber.e(it.toString())
            Timber.e(it.size.toString())
            adapter.submitList(it)})
        viewModel.viewState.observe(this , Observer {
            Timber.e(it.toString())
            binding.emptyView.visibility = if(it.empty!!) View.VISIBLE else View.GONE
        })

    }

}