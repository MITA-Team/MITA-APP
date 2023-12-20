package com.example.mita.data.api

import com.example.mita.data.response.LoginResponse
import com.example.mita.data.response.QuestionResponse
import com.example.mita.data.response.RegisterResponse
import com.example.mita.data.response.TherapyResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.Date

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("domicile") domicile: String,
        @Field("birthDate") birthDate: Date,
        @Field("password") password: String,
        @Field("confirmPass") confirmPass: String
    ): Response<RegisterResponse>

    // Fungsi login tetap sama
    @POST("users/login")
    fun login(@Body requestBody: RequestBody): Call<LoginResponse>

    @GET("question/all")
    suspend fun getAllQuestion(
        @Header("Authorization") token: String,
        @Part("Description") description: String
    ): QuestionResponse

    @POST("question/{id}")
    suspend fun getQuestionId(
        @Header("Authorization") token: String,
        @Path("id") accId: String,
        @Part("Description") description: String
    ): QuestionResponse

    @GET("therapy/all")
    suspend fun getAllTherapy(
        @Header("Authorization") token: String,
        @Part("Description") description: String
    )

    @GET("therapy/{id}")
    suspend fun getTherapyId(
        @Header("Authorization") token: String,
        @Path("id") accId: String,
        @Part("Description") description: String
    ): TherapyResponse
}