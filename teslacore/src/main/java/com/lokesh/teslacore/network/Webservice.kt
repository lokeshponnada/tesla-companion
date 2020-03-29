package com.lokesh.teslacore.network

import com.lokesh.teslacore.data.GenericResponse
import com.lokesh.teslacore.data.OwnedVehiclesResponse
import com.lokesh.teslacore.data.WakeUpResponse
import retrofit2.http.*

interface Webservice {

    @POST("1/vehicles/{id}/wake_up")
    suspend fun wakeUpVehicle(@Path("id") id:String): WakeUpResponse

    @GET("1/vehicles")
    suspend fun getOwnedVehicles(): OwnedVehiclesResponse

    @POST("1/vehicles/{id}/command/door_unlock")
    suspend fun unlockVehicle(@Path("id") id:String): GenericResponse

    @POST("1/vehicles/{id}/command/door_lock")
    suspend fun lockVehicle(@Path("id") id:String): GenericResponse

    @FormUrlEncoded
    @POST("1/vehicles/{id}/command/actuate_trunk")
    suspend fun openFrunk(@Path("id") id:String, @Field("which_trunk") frunkParam: String ): GenericResponse

    @POST("1/vehicles/{id}/command/charge_stop")
    suspend fun stopCharge(@Path("id") id:String): GenericResponse

    @POST("1/vehicles/{id}/command/charge_port_door_open")
    suspend fun openOrUnlockChargePort(@Path("id") id:String): GenericResponse

    @POST("1/vehicles/{id}/command/auto_conditioning_start")
    suspend fun turnOnClimate(@Path("id") id:String): GenericResponse

    @POST("1/vehicles/{id}/command/auto_conditioning_stop")
    suspend fun turnOffClimate(@Path("id") id:String): GenericResponse

    @FormUrlEncoded
    @POST("1/vehicles/{id}/command/remote_seat_heater_request")
    suspend fun heatSeat(@Path("id") id:String, @Field("heater") seatNumber:Int,@Field("level") heatLevel:Int): GenericResponse


}