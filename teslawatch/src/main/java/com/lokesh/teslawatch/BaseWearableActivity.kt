package com.lokesh.teslawatch

import android.annotation.SuppressLint
import android.content.Context
import android.support.wearable.activity.WearableActivity
import android.widget.Toast
import com.lokesh.teslacore.WatchApplication

open class BaseWearableActivity : WearableActivity() {


    protected fun toast(msg: String) {
        Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ApplySharedPref")
    protected fun writePref(key: String, value: String) {
        getSharedPreferences(WatchApplication.APP_PREF, Context.MODE_PRIVATE).edit()
            .putString(key, value).commit()
    }

    protected fun readPref(key: String): String {
        return getSharedPreferences(WatchApplication.APP_PREF, Context.MODE_PRIVATE).getString(
            key,
            ""
        )!!
    }
}