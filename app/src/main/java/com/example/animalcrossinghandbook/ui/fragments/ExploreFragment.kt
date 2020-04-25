package com.example.animalcrossinghandbook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.viewmodels.ExploreViewModel

class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        exploreViewModel =
                ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        exploreViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}
