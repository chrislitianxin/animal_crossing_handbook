package com.example.animalcrossinghandbook.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.Fish

/**
 * Bug Binding Adapters
 */

@BindingAdapter("bugName")
fun TextView.setBugName(item: Bug?) {
    item?.let{
        text = item.name
    }
}

@BindingAdapter("bugPrice")
fun TextView.setBugPrice(item: Bug?) {
    item?.let{
        text = item.price.toString()
    }
}

@BindingAdapter("bugIcon")
fun ImageView.setBugIcon(item: Bug?) {
    item?.let{
        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}


/**
 * Fish Binding Adapters
 */

@BindingAdapter("fishName")
fun TextView.setFishName(item: Fish?) {
    item?.let{
        text = item.name
    }
}

@BindingAdapter("fishPrice")
fun TextView.setFishPrice(item: Fish?) {
    item?.let{
        text = item.price.toString()
    }
}

@BindingAdapter("fishIcon")
fun ImageView.setFishIcon(item: Fish?) {
    item?.let{
        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}

