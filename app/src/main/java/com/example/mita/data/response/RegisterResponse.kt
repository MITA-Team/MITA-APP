package com.example.mita.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RegisterResponse(

	@field:SerializedName("data")
	val data: Datareg,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class Datareg(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("confirmPass")
	val confirmPass: String,

	@field:SerializedName("domicile")
	val domicile: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("birthDate")
	val birthDate: Date,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)

