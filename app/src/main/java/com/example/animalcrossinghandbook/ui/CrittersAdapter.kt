package com.example.animalcrossinghandbook.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.entities.Bug
import com.example.animalcrossinghandbook.entities.Fish
import java.util.*
import kotlin.reflect.KClass


class CrittersAdapter : RecyclerView.Adapter<CrittersAdapter.BaseViewHolder<*>>() {

    companion object {
        private const val TYPE_BUG = 0
        private const val TYPE_FISH = 1
        //private val TYPE_VILLAGER = 2
    }


//    private val data: MutableList<Comparable<*>>
//
//    init { data = ArrayList()
//    }
//
//    // TODO observe data change
//    fun swapData(newData: List<Comparable<*>>) {
//        data.clear()
//        data.addAll(newData)
//        notifyDataSetChanged()
//    }

//    var databug = mutableListOf<Comparable<*>>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }


    var data = MediatorLiveData<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun getItemCount() = databug.size + datafish.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)

        // inflate respective layout with items based on type
        return when (viewType) {
            TYPE_BUG -> {
                val view = layoutInflater.inflate(R.layout.list_item_bug, parent, false)
                BugViewHolder(view)
            }
            TYPE_FISH -> {
                val view = layoutInflater.inflate(R.layout.list_item_fish, parent, false)
                FishViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //val item = data[position]
        when (holder) {
            is BugViewHolder -> holder.bind(databug[position] as Bug)
            is FishViewHolder -> holder.bind(datafish[position] as Fish)
            else -> throw IllegalArgumentException("Invalid Data Type at $position")
        }
    }


    abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*  Having a BaseViewHolder to extend to multiple ViewHolder for different kind of items
         *  will make code a lot cleaner going forward
         */
        abstract fun bind(item: T)

    }


    class BugViewHolder(private val view: View) : BaseViewHolder<Bug>(view) {
        /* holding Bug Views */

        val res: Resources = view.context.resources

        // Binding
        // tells where and how the data should be accessed
        val name: TextView = view.findViewById(R.id.bug_name)
        private val price: TextView = view.findViewById(R.id.bug_price)
        private val icon: ImageView = view.findViewById(R.id.bug_icon)


        override fun bind(item: Bug) {

            name.text = item.name
            price.text = item.price.toString()

            // dynamically get drawable filename
            val drawableId: Int =
                res.getIdentifier(item.filename, "drawable", view.context.packageName)
            icon.setImageResource(drawableId)
        }

    }

    class FishViewHolder(private val view: View) : BaseViewHolder<Fish>(view) {
        /* holding Fish Views */
        val res: Resources = view.context.resources
        val name: TextView = view.findViewById(R.id.fish_name)
        private val price: TextView = view.findViewById(R.id.fish_price)
        private val icon: ImageView = view.findViewById(R.id.fish_icon)

        override fun bind(item: Fish) {

            name.text = item.name
            price.text = item.price.toString()

            val drawableId: Int =
                res.getIdentifier(item.filename, "drawable", view.context.packageName)
            icon.setImageResource(drawableId)
        }

    }


}