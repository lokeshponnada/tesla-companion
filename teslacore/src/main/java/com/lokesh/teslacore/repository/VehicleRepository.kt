package com.lokesh.teslacore.repository

import com.lokesh.teslacore.network.RetrofitClient
import com.lokesh.teslacore.network.Webservice

class VehicleRepository {

    companion object{

        private const val vehicleId = "YOUR_VEHICLE_ID_HERE"

        var client: Webservice = RetrofitClient.webservice

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