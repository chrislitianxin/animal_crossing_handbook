package com.example.animalcrossinghandbook.ui.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.animalcrossinghandbook.R
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.Fish
import kotlinx.android.synthetic.main.list_item_bug.view.*
import org.jetbrains.annotations.NotNull

class ListItemAdapter(private val resLayoutId: Int):
    BaseQuickAdapter<Any, BaseViewHolder>(resLayoutId, ArrayList()) {

    /**
     * Adapter for Common List Items
     *  Villager, Bug, Fish
     */




    /**
     *  Set item data here
     */
    override fun convert(@NotNull holder: BaseViewHolder, @NotNull item: Any) {
        when(item){
            is Bug -> convertBugs(holder, item)
            //is Fish -> convertFish(holder, item)
        }
    }


    private fun convertBugs(holder: BaseViewHolder, item: Bug) {
        holder.setText(R.id.bug_name, item.name)

        // !! convert item.price from Int? to Int
        holder.setText(R.id.bug_price, item.price!!.toString())

        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        holder.setImageResource(R.id.bug_icon, drawableId)

    }


    private fun convertFish(holder: BaseViewHolder, item: Fish) {
        holder.setText(R.id.fish_name, item.name)

        // !! convert item.price from Int? to Int
        holder.setText(R.id.fish_price, item.price!!.toString())

        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        holder.setImageResource(R.id.fish_icon, drawableId)

    }

}