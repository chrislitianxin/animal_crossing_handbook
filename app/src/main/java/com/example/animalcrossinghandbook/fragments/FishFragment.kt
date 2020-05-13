package com.example.animalcrossinghandbook.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.databinding.FragmentListFishBinding
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodels.FishViewModel


class FishFragment : Fragment() {

    private val mAdapter = ListItemAdapter(R.layout.list_item_fish)
    private val viewModel: FishViewModel by viewModels {
        InjectorUtils.provideFishViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Enable search in app bar
        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentListFishBinding>(
            inflater, R.layout.fragment_list_fish, container, false
        ).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            fishList.adapter = mAdapter
            fishList.layoutManager = GridLayoutManager(activity, 3)

        }

        subscribeUI()

        /**
         * Set card click navigation
         */
        mAdapter.setOnItemClickListener(OnItemClickListener { adapter, _, position ->
            val fishId = (adapter.getItem(position) as Fish).id
            findNavController().navigate(
                FishFragmentDirections.actionFishFragmentToFishDetailFragment(fishId)
            )
        })

        /**
         * Set long click in_museum toggle switch
         */
        mAdapter.setOnItemLongClickListener { _, _, position ->
            // notify data change
            val fishId = (mAdapter.getItem(position) as Fish).id
            viewModel.toggleInMuseumById(fishId)

            true
        }


        return binding.root
    }


    /**
     * Subscribe data to UI
     */
    private fun subscribeUI() {
        viewModel.fish.observe(viewLifecycleOwner, Observer {
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
