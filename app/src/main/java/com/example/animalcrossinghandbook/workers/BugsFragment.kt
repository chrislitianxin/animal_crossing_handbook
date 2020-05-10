package com.example.animalcrossinghandbook.workers

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.AnimalCrossingDatabase
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.databinding.FragmentListBugsBinding
import com.example.animalcrossinghandbook.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodelfactorys.BugsViewModelFactory
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel


class BugsFragment : Fragment() {

    private val mAdapter = ListItemAdapter(R.layout.list_item_bug)
    private val viewModel: BugsViewModel by viewModels {
        InjectorUtils.provideBugsViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Enable search in app bar
        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentListBugsBinding>(
            inflater, R.layout.fragment_list_bugs, container, false
        ).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            bugsList.adapter = mAdapter
            bugsList.layoutManager = GridLayoutManager(activity, 3)

        }

        subscribeUi(viewModel)

        /**
         * Set card click navigation
         */
        mAdapter.setOnItemClickListener(OnItemClickListener { adapter, _, position ->
            val bugId = (adapter.getItem(position) as Bug).id
            findNavController().navigate(
                BugsFragmentDirections.actionBugsFragmentToBugDetailFragment(bugId)
            )
        })

        /**
         * Set long click in_museum toggle switch
         */
        mAdapter.setOnItemLongClickListener { _, _, position ->
            // notify data change
            val bugId = (mAdapter.getItem(position) as Bug).id
            viewModel.toggleInMuseumById(bugId)

            true
        }


        return binding.root
    }


    /**
     * Subscribe data to UI
     */
    private fun subscribeUi(bugsViewModel: BugsViewModel) {
        //mAdapter.initList(bugsViewModel.items)
        viewModel.bugs.observe(viewLifecycleOwner, Observer {
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
