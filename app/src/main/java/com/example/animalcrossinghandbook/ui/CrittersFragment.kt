package com.example.animalcrossinghandbook.ui

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.entities.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.databinding.FragmentCrittersBinding

class CrittersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCrittersBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_critters, container,false)


        // reference to the application context
        val application = requireNotNull(this.activity).application

        // reference to DAO
        val dataSource = AnimalCrossingDatabase.getInstance(application).bugDao()
        val viewModelFactory = CrittersViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val crittersViewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(CrittersViewModel::class.java)


        /* Data binding for ViewModel */
        binding.setLifecycleOwner(this)

        // see fragment_critters.xml
        binding.crittersViewModel = crittersViewModel

        // attach adapter
        val adapter = CrittersAdapter()
        binding.crittersList.adapter = adapter

        crittersViewModel.bugs.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })


        return binding.root
    }

}
