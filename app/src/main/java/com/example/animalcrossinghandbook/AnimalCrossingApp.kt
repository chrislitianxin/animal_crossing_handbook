package com.example.animalcrossinghandbook

import android.app.Application
import timber.log.Timber

class AnimalCrossingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize timber logger
        Timber.plant(Timber.DebugTree())
    }

}