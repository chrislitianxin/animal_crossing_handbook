package com.example.animalcrossinghandbook.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.databinding.FragmentBugDetailBinding
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodels.BugDetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_bug_detail.*


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

        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentBugDetailBinding>(
            inflater, R.layout.fragment_bug_detail, container, false
        ).apply {
            viewModel = bugDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun toggleInMuseum(bugId: Int) {
                    bugDetailViewModel.toggleInMuseum()
                    Snackbar.make(root, R.string.added_to_museum, Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            /**
             * Set in_museum toggle switch
             */
            inMuseumToggle.setOnClickListener {
                bugDetailViewModel.toggleInMuseum()
            }

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
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
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_detail, menu)
//    }


    interface Callback {
        fun toggleInMuseum(bugId: Int)
    }
}