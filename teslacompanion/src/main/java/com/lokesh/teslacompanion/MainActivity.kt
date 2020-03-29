package com.lokesh.teslacompanion

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lokesh.teslacore.data.GenericResponse
import com.lokesh.teslacore.data.OwnedVehiclesResponse
import com.lokesh.teslacore.data.WakeUpResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel:MainActivityViewModel by viewModels()

    private val wakeUpObserver = Observer<WakeUpResponse> { vehicleWakeupAttempted(it) }
    private val ownedVehiclesObserver = Observer<OwnedVehiclesResponse> { updateOwnedVehicles(it) }
    private val vehicleUnLockObserver = Observer<GenericResponse> { updateVehicleUnLockStatus(it) }
    private val vehicleLockObserver = Observer<GenericResponse> { updateVehicleLockStatus(it)  }

    //Climate
    private val seatHeaterObserver = Observer<GenericResponse> { updateSeatHeaterSettings()  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Todo Extend ViewModel to pass these as a map inline
        viewModel.wakeUpResponse.observe(this,wakeUpObserver)
        viewModel.ownedVehicleList.observe(this,ownedVehiclesObserver)
        viewModel.unlockResponse.observe(this,vehicleUnLockObserver)
        viewModel.lockResponse.observe(this, vehicleLockObserver)

        setListeners()

    }

    private fun setListeners(){
        lockBtn.setOnClickListener { viewModel.lockVehicle() }
        unlockBtn.setOnClickListener { viewModel.unlockVehicle() }
        wakeupBtn.setOnClickListener { viewModel.wakeUpVehicle() }
    }

    private fun vehicleWakeupAttempted(wakeUpResponse: WakeUpResponse){

        when(wakeUpResponse.response?.state){
            "online" -> enableUI()
            "asleep" -> disableUI()
             else -> disableUI()
        }
    }

    private fun updateOwnedVehicles(vehicles: OwnedVehiclesResponse){
    }

    private fun updateVehicleUnLockStatus(response: GenericResponse){
        if (response.response.result){
            toast("Unlocked")
        }else{
            toast("Failed to Unlock")
        }
    }

    private fun updateVehicleLockStatus(response: GenericResponse) {
        if (response.response.result){
            toast("Locked")
        }else{
            toast("Failed to Lock")
        }
    }

    private fun updateSeatHeaterSettings(){

    }

    private fun enableUI() {
        toast("OnLine")
    }


    private fun disableUI(){
        toast("Sleepy")
    }
}
