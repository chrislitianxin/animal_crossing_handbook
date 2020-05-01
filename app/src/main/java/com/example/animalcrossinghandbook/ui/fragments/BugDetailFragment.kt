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
import com.example.animalcrossinghandbook.viewmodelfactorys.BugDetailViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.BugDetailViewModel

/**
 * Single Bug Detail Screen
 */
class BugDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBugDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_bug_detail, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = BugDetailFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = AnimalCrossingDatabase.getInstance(application).bugDao()
        val viewModelFactory = BugDetailViewModelFactory(arguments.itemId, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val bugDetailViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(BugDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.bugDetailViewModel = bugDetailViewModel

        binding.lifecycleOwner = this

//        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
//        bugDetailViewModel.navigateToSleepTracker.observe(this, Observer {
//            if (it == true) { // Observed state is true.
//                this.findNavController().navigate(
//                    BugDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
//                // Reset state to make sure we only navigate once, even if the device
//                // has a configuration change.
//                bugDetailViewModel.doneNavigating()
//            }
//        })

        return binding.root

    }

}