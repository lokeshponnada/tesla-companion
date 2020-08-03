package com.lokesh.teslawatch

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lokesh.teslacore.WatchApplication
import com.lokesh.teslacore.repository.VehicleRepository
import com.lokesh.teslacore.WatchApplication.Companion.PREF_KEY_AUTH_TOKEN
import com.lokesh.teslacore.WatchApplication.Companion.PREF_KEY_VEHICLE_ID
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : BaseWearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Todo Explore Lock
        if (WatchApplication.AUTH_TOKEN != null && WatchApplication.AUTH_TOKEN!!.isNotEmpty() && WatchApplication.VEHICLE_ID != null && WatchApplication.VEHICLE_ID!!.isNotEmpty()){
            // User already logged in with valid details , proceed to home page
            handleLoginSucess()
            return
        }

        btn_login.setOnClickListener {
            login_progressbar.visibility = View.VISIBLE
            GlobalScope.launch {
                login(username.text.toString(), password.text.toString())
            }
        }

    }

    private suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        val loginResponse = VehicleRepository.login(email, password)
        if (loginResponse.access_token != null) {
            // login success, store auth token , retrieve vehicle id
            writePref(PREF_KEY_AUTH_TOKEN, loginResponse.access_token!!)
            WatchApplication.AUTH_TOKEN = loginResponse.access_token!!
            getOwnedVehicles()
        } else {
            // login error
            handleLoginError("Invalid Credentials")
        }
    }


    private suspend fun getOwnedVehicles() {
        val ownedVehiclesResponse = VehicleRepository.getOwnedVehicles()
        if (ownedVehiclesResponse.count != null && ownedVehiclesResponse.count!! > 0) {
            // Get the first vehicle and proceed
            // Todo Support multiple vehicles
            val ownedVehicle = ownedVehiclesResponse.response?.get(0)
            if (ownedVehicle != null) {
                writePref(PREF_KEY_VEHICLE_ID, ownedVehicle.id.toString())
                WatchApplication.VEHICLE_ID = ownedVehicle.id.toString()
                handleLoginSucess()
            } else {
                // Shouldn't occur
                toast("Something went wrong, Please report on Github")
            }
        } else {
            handleLoginError("No owned vehicles found")
        }
    }

    private fun handleLoginSucess() {
        login_progressbar?.visibility = View.GONE
        val intent = Intent(this,MainActivityWear::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleLoginError(errorMsg: String = "Something Went Wrong"){
        login_progressbar.visibility = View.GONE
        toast(errorMsg)
    }
}
