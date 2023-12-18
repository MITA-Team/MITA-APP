package com.example.mita.data.api

import com.example.mita.data.response.LoginResponse
import com.example.mita.data.response.QuestionResponse
import com.example.mita.data.response.RegisterResponse
import com.example.mita.data.response.TherapyResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    fun register (
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("domicile") domicile: String,
        @Field("birthDate") birtDate: String,
        @Field("password") password: String,
        @Field("confirmPass") confirmPass: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("question/all")
    fun getAllQuestion(
        @Header("Authorization") token: String,
        @Part("Description") description: String
    ): QuestionResponse

    @POST("question/{id}")
    fun getQuestionId(
        @Header("Authorization") token: String,
        @Path("id") accId: String,
        @Part("Description") description: String
    ): QuestionResponse

    @GET("therapy/all")
    fun getAllTherapy(
        @Header("Authorization") token: String,
        @Part("Description") description: String
    )

    @GET("therapy/{id}")
    fun getTherapyId(
        @Header("Authorization") token: String,
        @Path("id") accId: String,
        @Part("Description") description: String
    ): TherapyResponse
}