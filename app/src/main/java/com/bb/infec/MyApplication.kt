package com.bb.infec

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.bb.infec.api.ApiClient
import com.bb.infec.api.Constants
import com.bb.infec.koin.appModule
import com.bb.infec.utils.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        setupWorker()
        startKoin {
            ApiClient.context = this@MyApplication
            androidContext(this@MyApplication)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            modules(listOf(appModule))
        }


    }

}