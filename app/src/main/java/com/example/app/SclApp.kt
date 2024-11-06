package com.example.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp



class SclApp : Application(){
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}

