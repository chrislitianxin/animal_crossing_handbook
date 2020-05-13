package com.example.animalcrossinghandbook.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.databinding.FragmentFishDetailBinding
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodels.FishDetailViewModel
import com.google.android.material.snackbar.Snackbar


/**
 * Single Fish Detail Screen
 */
class FishDetailFragment : Fragment() {

    private val args: FishDetailFragmentArgs by navArgs()

    private val fishDetailViewModel: FishDetailViewModel by viewModels {
        InjectorUtils.provideFishDetailViewModelFactory(requireActivity(), args.fishId)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        //requireActivity().actionBar!!.title = "hello world"

        val binding = DataBindingUtil.inflate<FragmentFishDetailBinding>(
            inflater, R.layout.fragment_fish_detail, container, false
        ).apply {
            viewModel = fishDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun toggleInMuseum(fishId: Int) {
                    fishDetailViewModel.toggleInMuseum()
                    Snackbar.make(root, R.string.added_to_museum, Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            /**
             * Set in_museum toggle switch
             */
            inMuseumToggle.setOnClickListener {
                fishDetailViewModel.toggleInMuseum()
            }
        }


        // change button text based on whether it has been donated
        // TODO changed to show/hide Blather icon
        fishDetailViewModel.isInMuseum.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> binding.inMuseumToggle.setText(R.string.donated)
                else -> binding.inMuseumToggle.setText(R.string.donate)
            }
        })

        return binding.root
    }


    // show share intent
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
    }


    interface Callback {
        fun toggleInMuseum(fishId: Int)
    }
}