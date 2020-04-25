package com.example.animalcrossinghandbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.databinding.FragmentListBugsBinding
import com.example.animalcrossinghandbook.ui.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.viewmodelfactorys.BugsViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel

class BugsFragment : Fragment() {
    /**
     * This fragment could totally be shared by fish and bug to be
     * CrittersFragment with just different data passed in
     * That will be more convoluted, separating bugs and fish
     * from each other so it can be easily extended in the future
     *
     * However, bug and fish fragment share the same layout res
     */

    private val viewModel: BugsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        /**
         * passing database dao to viewmodel
         */
        val application = requireNotNull(this.activity).application
        val dataSource = AnimalCrossingDatabase.getInstance(application).bugDao()
        val viewModelFactory = BugsViewModelFactory(dataSource, application)
        val sleepTrackerViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(BugsViewModel::class.java)


        val binding: FragmentListBugsBinding  = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_bugs, container, false
        )

        /**
         *  Grid layout
          */
        val manager = GridLayoutManager(activity, 2)
        binding.bugsList.layoutManager = manager

        /** Data binding for ViewModel
         */
        binding.lifecycleOwner = this

        // see fragment_list_bugs.xml
        binding.bugsViewModel = viewModel

        // attach adapter
        val adapter = ListItemAdapter(R.layout.list_item_bug)
        binding.bugsList.adapter = adapter

        subscribeUi(adapter)


        return binding.root
    }

    private fun subscribeUi(adapter: ListItemAdapter) {
        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setList(it)
            }
        })
    }

}
