package com.example.animalcrossinghandbook.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.databinding.FragmentListVillagersBinding
import com.example.animalcrossinghandbook.ui.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.viewmodelfactorys.VillagersViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.VillagersViewModel


class VillagersFragment : Fragment() {
    /**
     *
     */

    private val mAdapter = ListItemAdapter(R.layout.list_item_villager)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        //passing database dao to ViewModel via ViewModelFactory
        val application = requireNotNull(this.activity).application
        val dataSource = AnimalCrossingDatabase.getInstance(application).villagerDao()

        val viewModelFactory = VillagersViewModelFactory(dataSource, application)
        val villagersViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(VillagersViewModel::class.java)


        // Enable search in app bar
        setHasOptionsMenu(true)

        val binding: FragmentListVillagersBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_villagers, container, false
        )

        //Grid layout
        val manager = LinearLayoutManager(activity)
        binding.villagersList.layoutManager = manager

        //Data binding for ViewModel
        binding.lifecycleOwner = this

        // see fragment_list_bugs.xml
        binding.villagersViewModel = villagersViewModel

        // set and attach adapter
        binding.villagersList.adapter = mAdapter
        mAdapter.animationEnable = true

        subscribeUi(villagersViewModel)

        /**
         * Set card click navigation
         */
        mAdapter.setOnItemClickListener(OnItemClickListener { adapter, _, position ->
            val id: Int = (adapter.getItem(position) as Villager).id
            findNavController().navigate(
                VillagersFragmentDirections.actionVillagersFragmentToVillagerDetailFragment(id)
            )
            villagersViewModel.onItemDetailNavigated()
        })

        return binding.root
    }


    /**
     * Subscribe data to UI
     */
    private fun subscribeUi(viewModel: VillagersViewModel) {
        //mAdapter.initList(bugsViewModel.items)
        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.setList(it)
            }
        })
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val menuItem = menu.findItem(R.id.item_search)
        menuItem.isVisible = true

        val searchView =
            menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newQuery: String): Boolean {
                mAdapter.filter.filter(newQuery)
                return true
            }
        })
    }


}
