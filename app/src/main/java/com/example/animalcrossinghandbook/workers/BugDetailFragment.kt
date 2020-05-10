package com.example.animalcrossinghandbook.workers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.databinding.FragmentBugDetailBinding
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodelfactorys.BugDetailViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.BugDetailViewModel


/**
 * Single Bug Detail Screen
 */
class BugDetailFragment : Fragment() {

    private val args: BugDetailFragmentArgs by navArgs()

    private val bugDetailViewModel: BugDetailViewModel by viewModels {
        InjectorUtils.provideBugDetailViewModelFactory(requireActivity(), args.bugId)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBugDetailBinding>(
            inflater, R.layout.fragment_bug_detail, container, false
        ).apply {
            viewModel = bugDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun toggleInMuseum(bugId: Int) {
                    bugDetailViewModel.toggleInMuseum()
                }
            }

            /**
             * Set in_museum toggle switch
             */
            inMuseumToggle.setOnClickListener {
                bugDetailViewModel.toggleInMuseum()
            }

        }


        // change button text based on whether it has been donated
        // TODO changed to show/hide Blather icon
        bugDetailViewModel.isInMuseum.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> binding.inMuseumToggle.setText(R.string.donated)
                else -> binding.inMuseumToggle.setText(R.string.donate)
            }
        })

        return binding.root

    }


    interface Callback {
        fun toggleInMuseum(bugId: Int)
    }
}