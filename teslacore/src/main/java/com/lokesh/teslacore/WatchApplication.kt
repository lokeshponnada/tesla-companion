package com.lokesh.teslacore

import android.app.Application
import android.content.Context

class WatchApplication : Application() {



    companion object{
        val PREF_KEY_AUTH_TOKEN = "auth_token"
        val PREF_KEY_VEHICLE_ID = "vehicle_id"
        val APP_PREF = "app_prefs"

        var AUTH_TOKEN:String = ""
        var VEHICLE_ID:String = ""

    }

    override fun onCreate() {
        super.onCreate()
        AUTH_TOKEN = getSharedPreferences(
            APP_PREF, Context.MODE_PRIVATE).getString(
            PREF_KEY_AUTH_TOKEN,"")!!
        VEHICLE_ID = getSharedPreferences(
            APP_PREF, Context.MODE_PRIVATE).getString(
            PREF_KEY_VEHICLE_ID,"")!!
    }
}