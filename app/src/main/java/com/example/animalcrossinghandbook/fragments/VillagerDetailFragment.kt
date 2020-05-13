package com.example.animalcrossinghandbook.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.databinding.FragmentVillagerDetailBinding
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodels.VillagerDetailViewModel

/**
 * Single Villager Detail Screen
 */
class VillagerDetailFragment : Fragment() {

    private val args: VillagerDetailFragmentArgs by navArgs()

    private val villagerDetailViewModel: VillagerDetailViewModel by viewModels {
        InjectorUtils.provideVillagerDetailViewModelFactory(requireActivity(), args.villagerId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentVillagerDetailBinding>(
            inflater, R.layout.fragment_villager_detail, container, false
        ).apply {
            viewModel = villagerDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun toggleIsResident(villagerId: Int) {
                    villagerDetailViewModel.toggleIsResident()
                }
            }

            /**
             * Set is_resident toggle switch
             */
            isResidentToggle.setOnClickListener {
                villagerDetailViewModel.toggleIsResident()
            }
        }



        villagerDetailViewModel.isResident.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> binding.isResidentToggle.setText(R.string.donated)
                else -> binding.isResidentToggle.setText(R.string.donate)
            }
        })

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
    }

    interface Callback {
        fun toggleIsResident(villagerId: Int)
    }

}