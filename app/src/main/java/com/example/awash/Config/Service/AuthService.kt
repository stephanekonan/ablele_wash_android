package com.example.awash.Config.Service

import com.example.awash.Config.Request.LoginRequest
import com.example.awash.Config.Request.RegisterRequest
import com.example.awash.Config.Models.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>

    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<AuthResponse>

    @POST("logout")
    fun logout(@Header("Authorization") token: String): Call<Void>
}
