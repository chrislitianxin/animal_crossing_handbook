package com.example.animalcrossinghandbook.adapters

import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.util.IMAGE_BASE_PATH
import java.io.IOException


/**
 * Bug Binding Adapters
 */

@BindingAdapter("bugPrice")
fun TextView.setBugPrice(price: Int) {
    price.let {
        text = price.toString()
    }
}

@BindingAdapter("bugIcon")
fun ImageView.setBugIcon(filename: String?) {
    filename.let {
        val drawableId: Int =
            context.resources.getIdentifier(filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}

@BindingAdapter("bugImage")
fun ImageView.setBugImage(filename: String?) {
    filename?.let {
        val fileUri = IMAGE_BASE_PATH + "/bugs/${filename}.png"
        try {
            context.assets.open(fileUri).use {
                setImageBitmap(BitmapFactory.decodeStream(it))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}


/**
 * Fish Binding Adapters
 */


@BindingAdapter("fishPrice")
fun TextView.setFishPrice(price: Int) {
    price.let {
        text = price.toString()
    }
}

@BindingAdapter("fishIcon")
fun ImageView.setFishIcon(filename: String?) {
    filename.let {
        val drawableId: Int =
            context.resources.getIdentifier(filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}

@BindingAdapter("fishImage")
fun ImageView.setFishImage(filename: String?) {
    filename.let {
        val fileUri = IMAGE_BASE_PATH + "/fish/${filename}.png"
        try {
            context.assets.open(fileUri).use {
                setImageBitmap(BitmapFactory.decodeStream(it))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}


/**
 * Villager Binding Adapters
 */

@BindingAdapter("villagerIcon")
fun ImageView.setVillagerIcon(filename: String?) {
    filename.let {
        val drawableId: Int =
            context.resources.getIdentifier(filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}

@BindingAdapter("villagerImage")
fun ImageView.setVillagerImage(filename: String?) {
    filename?.let {
        val fileUri = IMAGE_BASE_PATH + "/villagers/${filename}.png"

        try {
            context.assets.open(fileUri).use {
                setImageBitmap(BitmapFactory.decodeStream(it))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

//        Glide.with(this.context)
//            .load(fileUri)
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .into(this)
    }
}


