package com.example.animalcrossinghandbook.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.databinding.FragmentListVillagersBinding
import com.example.animalcrossinghandbook.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodels.VillagersViewModel


class VillagersFragment : Fragment() {


    private val mAdapter = ListItemAdapter(R.layout.list_item_villager)
    private val viewModel: VillagersViewModel by viewModels {
        InjectorUtils.provideVillagersViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Enable search in app bar
        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentListVillagersBinding>(
            inflater, R.layout.fragment_list_villagers, container, false
        ).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            villagersList.adapter = mAdapter
            villagersList.layoutManager = LinearLayoutManager(activity)
        }


        subscribeUI()

        /**
         * Set card click navigation
         */
        mAdapter.setOnItemClickListener(OnItemClickListener { adapter, _, position ->
            val villagerId: Int = (adapter.getItem(position) as Villager).id
            findNavController().navigate(
                VillagersFragmentDirections.actionVillagersFragmentToVillagerDetailFragment(
                    villagerId
                )
            )
        })


        return binding.root
    }


    /**
     * Subscribe data to UI
     */
    private fun subscribeUI() {
        viewModel.villagers.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.setList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
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
