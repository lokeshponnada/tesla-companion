package com.lokesh.teslacompanion

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected fun log(msg: String){
        Log.d("lokesh",msg)
    }


    protected fun toast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    protected fun longToast(msg: String){
    }
}