package com.example.animalcrossinghandbook.ui.adapters

import android.annotation.SuppressLint
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.databinding.ListItemBugBinding
import org.jetbrains.annotations.NotNull
import timber.log.Timber


class ListItemAdapter(
    private val resLayoutId: Int
) : BaseQuickAdapter<Any, BaseViewHolder>(resLayoutId, ArrayList()),
    Filterable {
    /**
     * Adapter for Common List Items As a RecyclerView
     *  Villager, Bug, Fish
     */
    private var mSourceList: List<Any> = ArrayList()
    private var mFilterList: List<Any> = ArrayList()
    // cannot lateinit, getCount() needs this


    init {
        initList(data)
        setDiffCallback(DiffListItemCallback())
    }

    // this callback will be executed when ViewHolder is created
    override fun onItemViewHolderCreated(
        viewHolder: BaseViewHolder,
        viewType: Int
    ) {
        // binding view
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)

    }


    //Set item data here
    override fun convert(@NotNull holder: BaseViewHolder, @NotNull item: Any) {
        when (item) {
            is Bug -> convertBugs(holder, item)
            //is Fish -> convertFish(holder, item)
            else -> Timber.e("Unhandled conversion type")
        }
    }

    // converter for Bug items
    private fun convertBugs(holder: BaseViewHolder, item: Bug) {

        val binding: ListItemBugBinding? = DataBindingUtil.getBinding(holder.itemView)

        if (binding != null) {
            // set data
            binding.bug = item
            binding.executePendingBindings()
        }
    }

    //    private fun convertFish(holder: BaseViewHolder, item: Fish) {
//
//        val binding: ListItemFishBinding? = DataBindingUtil.getBinding(holder.itemView)
//
//        if (binding != null) {
//            // set data
//            binding.fish = item
//            binding.executePendingBindings()
//        }
//    }


    /**
     * Initialize lists to be filtered
     */
    fun initList(list: List<Any>) {
        mSourceList = list
        mFilterList = list
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                mFilterList = if (charString.isEmpty()) {
                    // no filter entered, use origianl
                    mSourceList
                } else {
                    val filteredList: MutableList<Any> = ArrayList()
                    for (item in mSourceList) {
                        if (shouldBeFiltered(item, charString)) filteredList.add(item)
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = mFilterList
                return filterResults
            }

            override fun publishResults(
                constraint: CharSequence,
                results: FilterResults
            ) {
                val list: MutableList<Any>? =
                    results.values as? MutableList<Any> // safe cast, neat!
                //val callback = DiffListItemCallback(list)
                setDiffNewData(list)
            }


            /**
             * Contains the logic for filtering
             */
            private fun shouldBeFiltered(item: Any, filterString: String): Boolean {
                val filterString = filterString.toLowerCase().trim { it <= ' ' }

                val itemName = when (item) {
                    is Bug -> item.name.toLowerCase()
                    is Fish -> item.name.toLowerCase()
                    else -> {
                        Timber.e("Filtering Type unhandled")
                        ""
                    }
                }
                return itemName.contains(filterString)
            }
        }
    }


}


class DiffListItemCallback() :
    DiffUtil.ItemCallback<Any>() {
    /**
     * Checking if items are the same
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is Bug && newItem is Bug -> oldItem.id == newItem.id
            oldItem is Fish && newItem is Fish -> oldItem.id == newItem.id
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is Bug && newItem is Bug -> oldItem == newItem
            oldItem is Fish && newItem is Fish -> oldItem == newItem
            else -> false
        }
    }
}
