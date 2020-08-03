package com.lokesh.teslacore.repository

import com.lokesh.teslacore.BuildConfig
import com.lokesh.teslacore.network.RetrofitClient
import com.lokesh.teslacore.network.Webservice

class VehicleRepository {

    companion object{

        private const val vehicleId = BuildConfig.VEHICLE_ID

        private const val CLIENT_ID = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
        private const val CLIENT_SECRET = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";

        var client: Webservice = RetrofitClient.webservice

        suspend fun login(email:String,password:String) = client.login(email,password, CLIENT_SECRET,
            CLIENT_ID,"password")

        suspend fun getOwnedVehicles() = client.getOwnedVehicles()

        suspend fun wakeUpVehicle() = client.wakeUpVehicle(vehicleId)

        suspend fun unlockVehicle() = client.unlockVehicle(vehicleId)

        suspend fun lockVehicle() = client.lockVehicle(vehicleId)

        suspend fun openFrunk() = client.openFrunk(vehicleId,"front")

        suspend fun stopCharging() = client.stopCharge(vehicleId)

        suspend fun openOrUnlockChargePort() = client.openOrUnlockChargePort(vehicleId);

        suspend fun turnOnClimate() = client.turnOnClimate(vehicleId)

        suspend fun turnOffClimate() = client.turnOffClimate(vehicleId)

        suspend fun heatSeat(seat: Int,level: Int) = client.heatSeat(vehicleId,seat,level)

    }


}