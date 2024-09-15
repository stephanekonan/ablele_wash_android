package com.example.awash.Config.Models

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val cni: String,
    val phone: String,
    val password: String
)