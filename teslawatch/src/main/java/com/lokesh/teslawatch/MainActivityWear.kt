package com.lokesh.teslawatch

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.widget.Toast
import com.lokesh.teslacore.data.GenericResponse
import com.lokesh.teslacore.data.WakeUpResponse
import com.lokesh.teslacore.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.android.synthetic.main.activity_main.*


class MainActivityWear : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAmbientEnabled()

        GlobalScope.launch { wakeupVehicle() }

        setListeners()

    }

    private fun setListeners() {

        wake_btn.setOnClickListener { GlobalScope.launch { wakeupVehicle() } }

        lock_btn.setOnClickListener { GlobalScope.launch { lockVehicle() } }

        unlock_btn.setOnClickListener { GlobalScope.launch { unlockVehicle() } }

        frunk_btn.setOnClickListener { GlobalScope.launch { openFrunk() } }

        climate_btn.setOnClickListener { GlobalScope.launch { turnOnClimate() } }

        charge_unlock_btn.setOnClickListener { GlobalScope.launch { openOrUnlockChargePort() } }

    }


    private suspend fun wakeupVehicle() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.WAKEUP_VEHICLE, VehicleRepository.wakeUpVehicle())
    }

    private suspend fun lockVehicle() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.LOCK_VEHICLE, VehicleRepository.lockVehicle())
    }

    private suspend fun unlockVehicle() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.UNLOCK_VEHICLE, VehicleRepository.unlockVehicle())
    }

    private suspend fun openFrunk() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.OPEN_FRUNK, VehicleRepository.openFrunk())
    }

    private suspend fun stopCharging() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.STOP_CHARGING, VehicleRepository.stopCharging())
    }

    private suspend fun openOrUnlockChargePort() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.UNLOCK_CHARGE_PORT, VehicleRepository.openOrUnlockChargePort())
    }

    private suspend fun turnOnClimate() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.TURN_ON_CLIMATE, VehicleRepository.turnOnClimate())
    }

    private suspend fun turnOffClimate() = withContext(Dispatchers.IO) {
        handleResponse(OPERATION.TURN_OFF_CLIMATE, VehicleRepository.turnOffClimate())
    }

    private fun handleResponse(operation: OPERATION, response: Any) {

        var res = "N/A"

        if (response is WakeUpResponse) {
            if (response.error != null && response.error!!.isNotEmpty()) {
                res = "Error Wakingup"
            } else {
                res = "Wakeup successful "
            }
        } else if (response is GenericResponse) {
            res = if (response.response.result) "success" else "failure"
        }

        runOnUiThread {
            toast("$operation $res")
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    enum class OPERATION {
        WAKEUP_VEHICLE,
        LOCK_VEHICLE,
        UNLOCK_VEHICLE,
        OPEN_FRUNK,
        OPEN_TRUNK,
        OPEN_CHARGE_PORT,
        UNLOCK_CHARGE_PORT,
        STOP_CHARGING,
        TURN_ON_CLIMATE,
        TURN_OFF_CLIMATE
    }

}
