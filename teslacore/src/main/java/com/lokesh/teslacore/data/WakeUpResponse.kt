package com.lokesh.teslacore.data

data class WakeUpResponse(
    val response: VehicleInfo?,
    val error: String?,
    val errorDescription:String?
)