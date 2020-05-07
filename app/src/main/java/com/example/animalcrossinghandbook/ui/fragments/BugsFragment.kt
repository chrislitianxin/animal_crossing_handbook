package com.example.animalcrossinghandbook.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.databinding.FragmentListBugsBinding
import com.example.animalcrossinghandbook.ui.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.viewmodelfactorys.BugsViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel

/**
 *
 */
class BugsFragment : Fragment() {
    /**
     * This fragment could totally be shared by fish and bug to be
     * CrittersFragment with just different data passed in
     * That will be more convoluted, separating bugs and fish
     * from each other so it can be easily extended in the future
     *
     * However, bug and fish fragment share the same layout res
     */

    private val mAdapter = ListItemAdapter(R.layout.list_item_bug)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        //passing database dao to ViewModel via ViewModelFactory
        val application = requireNotNull(this.activity).application
        val dataSource = AnimalCrossingDatabase.getInstance(application).bugDao()

        val viewModelFactory = BugsViewModelFactory(dataSource, application)
        val bugsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(BugsViewModel::class.java)


        // Enable search in app bar
        setHasOptionsMenu(true)

        val binding: FragmentListBugsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_bugs, container, false
        )

        //Grid layout
        val manager = GridLayoutManager(activity, 3)
        binding.bugsList.layoutManager = manager

        //Data binding for ViewModel
        binding.lifecycleOwner = this

        // see fragment_list_bugs.xml
        binding.bugsViewModel = bugsViewModel

        // set and attach adapter
        binding.bugsList.adapter = mAdapter
        mAdapter.animationEnable = true

        subscribeUi(bugsViewModel)

        /**
         * Set card click navigation
         */
        mAdapter.setOnItemClickListener(OnItemClickListener { adapter, _, position ->
            val bugId: Int = (adapter.getItem(position) as Bug).id
            findNavController().navigate(
                BugsFragmentDirections.actionBugsFragmentToBugDetailFragment(bugId)
            )
            bugsViewModel.onItemDetailNavigated()
        })

        /**
         * Set in_museum toggle switch
         */



        return binding.root
    }


    /**
     * Subscribe data to UI
     */
    private fun subscribeUi(bugsViewModel: BugsViewModel) {
        //mAdapter.initList(bugsViewModel.items)
        bugsViewModel.items.observe(viewLifecycleOwner, Observer {
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
