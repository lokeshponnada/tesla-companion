package com.lokesh.teslawatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lokesh.teslacore.repository.VehicleRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener {  }
    }



    private suspend fun login(email: String, password: String) = withContext(Dispatchers.IO){
        val response = VehicleRepository.login(email,password)
        // validate response
        Log.d("LoginAct",response.toString())
    }
}
