package com.example.animalcrossinghandbook.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.databinding.FragmentListBugsBinding
import com.example.animalcrossinghandbook.adapters.ListItemAdapter
import com.example.animalcrossinghandbook.util.InjectorUtils
import com.example.animalcrossinghandbook.viewmodels.BugsViewModel
import kotlinx.android.synthetic.main.fragment_list_bugs.*
import timber.log.Timber


class BugsFragment : Fragment() {

    private val mAdapter = ListItemAdapter(R.layout.list_item_bug)
    private val viewModel: BugsViewModel by viewModels {
        InjectorUtils.provideBugsViewModelFactory(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val binding = DataBindingUtil.inflate<FragmentListBugsBinding>(
            inflater, R.layout.fragment_list_bugs, container, false
        ).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            bugsList.adapter = mAdapter
            bugsList.layoutManager = GridLayoutManager(activity, 3)

        }

        // attach toolbar to the fragment, otherwise search filtering does not work
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)


        subscribeUI()

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


        // Enable search in app bar
        setHasOptionsMenu(true)

        return binding.root
    }


    /**
     * Subscribe data to UI
     */
    private fun subscribeUI() {
        viewModel.bugs.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.setList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
        Timber.i("called")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val menuItem = menu.findItem(R.id.action_item_search)
        Timber.i("Search")
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
