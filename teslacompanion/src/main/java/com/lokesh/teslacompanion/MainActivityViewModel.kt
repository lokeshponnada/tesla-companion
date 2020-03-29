package com.lokesh.teslacompanion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokesh.teslacore.data.GenericResponse
import com.lokesh.teslacore.data.OwnedVehiclesResponse
import com.lokesh.teslacore.data.WakeUpResponse
import com.lokesh.teslacore.repository.VehicleRepository
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    //Todo Dagger

    val wakeUpResponse = MutableLiveData<WakeUpResponse>()
    val ownedVehicleList = MutableLiveData<OwnedVehiclesResponse>()
    val lockResponse = MutableLiveData<GenericResponse>()
    val unlockResponse = MutableLiveData<GenericResponse>()
    val seatHeaterResponse = MutableLiveData<GenericResponse>()

    init {
        fetchOwnedVehicleList()
        wakeUpVehicle()
    }

    fun unlockVehicle(){
        viewModelScope.launch {
            unlockResponse.value = VehicleRepository.unlockVehicle()
        }
    }

    fun lockVehicle(){
        viewModelScope.launch {
            lockResponse.value = VehicleRepository.lockVehicle()
        }
    }

    fun wakeUpVehicle(){
        viewModelScope.launch {
            wakeUpResponse.value = VehicleRepository.wakeUpVehicle()
        }
    }

    fun heatSeats(seatNumber: Int, heatLevel: Int){
        viewModelScope.launch {
            seatHeaterResponse.value = VehicleRepository.heatSeat(seatNumber,heatLevel)
        }
    }

    private fun fetchOwnedVehicleList(){
        viewModelScope.launch {
            ownedVehicleList.value = VehicleRepository.getOwnedVehicles()
        }
    }


}