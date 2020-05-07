package com.example.animalcrossinghandbook.util

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.data.Villager
import java.io.IOException
import java.io.InputStream


/**
 * Bug Binding Adapters
 */

@BindingAdapter("bugName")
fun TextView.setBugName(item: Bug?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("bugPrice")
fun TextView.setBugPrice(item: Bug?) {
    item?.let {
        text = item.price.toString()
    }
}

@BindingAdapter("bugIcon")
fun ImageView.setBugIcon(item: Bug?) {
    item?.let {
        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}

@BindingAdapter("bugImage")
fun ImageView.setBugImage(item: Bug?) {
    item?.let {
        val fileUri = "img/bugs/${item.filename}.png"
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

@BindingAdapter("fishName")
fun TextView.setFishName(item: Fish?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("fishPrice")
fun TextView.setFishPrice(item: Fish?) {
    item?.let {
        text = item.price.toString()
    }
}

@BindingAdapter("fishIcon")
fun ImageView.setFishIcon(item: Fish?) {
    item?.let {
        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}


/**
 * Villager Binding Adapters
 */

@BindingAdapter("villagerName")
fun TextView.setVillagerName(item: Villager?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("villagerPersonality")
fun TextView.setVillagerPersonality(item: Villager?) {
    item?.let {
        text = item.personality
    }
}

@BindingAdapter("villagerGender")
fun TextView.setVillagerGender(item: Villager?) {
    item?.let {
        text = item.gender
    }
}

@BindingAdapter("villagerSpecies")
fun TextView.setVillagerSpecies(item: Villager?) {
    item?.let {
        text = item.species
    }
}

@BindingAdapter("villagerBirthday")
fun TextView.setVillagerBirthday(item: Villager?) {
    // TODO prettify birthday text string
    item?.let {
        text = item.birthday
    }
}

@BindingAdapter("villagerIcon")
fun ImageView.setVillagerIcon(item: Villager?) {
    item?.let {
        val drawableId: Int =
            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
        setImageResource(drawableId)
    }
}

@BindingAdapter("villagerImage")
fun ImageView.setVillagerImage(item: Villager?) {
    item?.let {
        // TODO for some reason just couldn't get glide working.
//        val iconId: Int =
//            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
//        val imgUri = "assets://img/villagers/${item.filename}.png"
//
//        Glide.with(context)
//            .load(Uri.parse(imgUri))
//            .error(iconId) // just load icon if error encountered
//            .into(this)

        val fileUri = "img/villagers/${item.filename}.png"

        try {
            context.assets.open(fileUri).use {
                setImageBitmap(BitmapFactory.decodeStream(it))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}


