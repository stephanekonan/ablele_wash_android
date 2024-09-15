package com.example.awash.Config.Shared

import com.example.awash.Config.Service.AuthService
import com.example.awash.Config.Service.VehiculeService

object ApiServices {

    val authService: AuthService by lazy {
        RetrofitClient.createService(AuthService::class.java)
    }

    val vehiculeService: VehiculeService by lazy {
        RetrofitClient.createService(VehiculeService::class.java)
    }
}
