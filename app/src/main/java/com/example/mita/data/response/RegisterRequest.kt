package com.example.mita.data.response

data class RegisterRequest(
    val username: String,
    val email: String,
    val domicile: String,
    val birthDate: String,
    val password: String,
    val confirmPass: String
)
