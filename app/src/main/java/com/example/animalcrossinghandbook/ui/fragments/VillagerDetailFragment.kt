package com.example.animalcrossinghandbook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.databinding.FragmentBugDetailBinding
import com.example.animalcrossinghandbook.databinding.FragmentVillagerDetailBinding
import com.example.animalcrossinghandbook.viewmodelfactorys.VillagerDetailViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.VillagerDetailViewModel

/**
 * Single Villager Detail Screen
 */
class VillagerDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentVillagerDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_villager_detail, container, false
        )

        val application = requireNotNull(this.activity).application

        val arguments = VillagerDetailFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = AnimalCrossingDatabase.getInstance(application).villagerDao()
        val viewModelFactory = VillagerDetailViewModelFactory(arguments.itemId, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val villagerDetailViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(VillagerDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.villagerDetailViewModel = villagerDetailViewModel

        binding.lifecycleOwner = this

        return binding.root

    }

}