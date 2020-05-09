package com.example.animalcrossinghandbook.workers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.databinding.FragmentBugDetailBinding
import com.example.animalcrossinghandbook.viewmodelfactorys.BugDetailViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.BugDetailViewModel


/**
 * Single Bug Detail Screen
 */
class BugDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBugDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_bug_detail, container, false
        )

        val application = requireNotNull(this.activity).application

        val arguments = BugDetailFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = AnimalCrossingDatabase.getInstance(application).bugDao()
        val viewModelFactory = BugDetailViewModelFactory(arguments.itemId, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val bugDetailViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(BugDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.bugDetailViewModel = bugDetailViewModel

        binding.lifecycleOwner = this


        /**
         * Set in_museum toggle switch
         */
        binding.inMuseumToggle.setOnClickListener {
            bugDetailViewModel.toggleInMuseum()
        }

        // change button text based on whether it has been donated
        // TODO changed to Blather icon
        bugDetailViewModel.bug.observe(viewLifecycleOwner, Observer {
            when (it.inMuseum) {
                true -> binding.inMuseumToggle.setText(R.string.donated)
                false -> binding.inMuseumToggle.setText(R.string.donate)
            }
        })

        return binding.root

    }

}