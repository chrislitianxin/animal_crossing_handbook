package com.example.animalcrossinghandbook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.databinding.FragmentExploreBinding
import com.example.animalcrossinghandbook.viewmodels.ExploreViewModel
import kotlinx.android.synthetic.main.fragment_explore.view.*

class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        exploreViewModel =
            ViewModelProviders.of(this).get(ExploreViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentExploreBinding>(
            inflater, R.layout.fragment_explore, container, false
        )
        // bind this fragment class to layout
        binding.explore = this
        //val root = inflater.inflate(R.layout.fragment_explore, container, false)
        binding.bugButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ExploreFragmentDirections.actionNavigationExploreToBugsFragment())
        }
        return binding.root
    }
}
