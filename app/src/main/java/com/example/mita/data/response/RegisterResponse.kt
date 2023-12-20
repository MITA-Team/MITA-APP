package com.example.mita.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: Datareg,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class Datareg(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("domicile")
	val domicile: String,

	@field:SerializedName("birthDate")
	val birthDate: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("confirmPass")
	val confirmPass: String,


)

