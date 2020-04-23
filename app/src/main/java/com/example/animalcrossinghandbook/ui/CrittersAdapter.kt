package com.example.animalcrossinghandbook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.entities.Bug


class CrittersAdapter : RecyclerView.Adapter<CrittersAdapter.ViewHolder>() {

    var data = listOf<Bug>()
        set(value) {
            field = value
            // tell recyclerview that entire list is updated,
            // will cause everythin redraw, expensive
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }



    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Binding
        // tells where and how the data should be accessed
        val name: TextView = itemView.findViewById(R.id.critter_name)
        val price: TextView = itemView.findViewById(R.id.critter_price)
        //val thumbnail: TextView = itemView.findViewById(R.id.critter_thumbnail)


        fun bind(item: Bug) {
            // val res = itemView.context.resources
            name.text = item.name
            price.text = item.price.toString()
            // TODO, add thumbnail to dynamic image from res
            //  holder.thumbnail = item.filename
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_critter, parent, false)
                return ViewHolder(view)
            }
        }
    }


}