package com.lokesh.teslacore.repository

import com.lokesh.teslacore.WatchApplication
import com.lokesh.teslacore.network.RetrofitClient
import com.lokesh.teslacore.network.Webservice

class VehicleRepository {

    companion object{
        
        private const val CLIENT_ID = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
        private const val CLIENT_SECRET = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";

        var client: Webservice = RetrofitClient.webservice

        suspend fun login(email:String,password:String) = client.login(email,password, CLIENT_SECRET,
            CLIENT_ID,"password")

        suspend fun getOwnedVehicles() = client.getOwnedVehicles()

        suspend fun wakeUpVehicle() = client.wakeUpVehicle(WatchApplication.VEHICLE_ID)

        suspend fun unlockVehicle() = client.unlockVehicle(WatchApplication.VEHICLE_ID)

        suspend fun lockVehicle() = client.lockVehicle(WatchApplication.VEHICLE_ID)

        suspend fun openFrunk() = client.openFrunk(WatchApplication.VEHICLE_ID,"front")

        suspend fun stopCharging() = client.stopCharge(WatchApplication.VEHICLE_ID)

        suspend fun openOrUnlockChargePort() = client.openOrUnlockChargePort(WatchApplication.VEHICLE_ID);

        suspend fun turnOnClimate() = client.turnOnClimate(WatchApplication.VEHICLE_ID)

        suspend fun turnOffClimate() = client.turnOffClimate(WatchApplication.VEHICLE_ID)

        suspend fun heatSeat(seat: Int,level: Int) = client.heatSeat(WatchApplication.VEHICLE_ID,seat,level)

    }


}