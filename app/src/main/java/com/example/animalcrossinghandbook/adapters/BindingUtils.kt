package com.example.animalcrossinghandbook.adapters

import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.animalcrossinghandbook.data.Bug
import com.example.animalcrossinghandbook.data.Fish
import com.example.animalcrossinghandbook.data.Villager
import com.example.animalcrossinghandbook.util.IMAGE_BASE_PATH
import java.io.IOException


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

@BindingAdapter("fishImage")
fun ImageView.setFishImage(item: Fish?) {
    item?.let {
        val fileUri = IMAGE_BASE_PATH + "/fish/${item.filename}.png"
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
        // TODO
        //  for some reason just couldn't get glide working.
        //  Maybe there are more flexible ways to do this other than setImageBitmap
//        val iconId: Int =
//            context.resources.getIdentifier(item.filename, "drawable", context.packageName)
//        val imgUri = "assets://img/villagers/${item.filename}.png"
//
//        Glide.with(context)
//            .load(Uri.parse(imgUri))
//            .error(iconId) // just load icon if error encountered
//            .into(this)

        val fileUri = IMAGE_BASE_PATH + "/villagers/${item.filename}.png"

//        try {
//            context.assets.open(fileUri).use {
//                setImageBitmap(BitmapFactory.decodeStream(it))
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }

        Glide.with(this.context)
            .load(fileUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}


