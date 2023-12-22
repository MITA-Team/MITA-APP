package com.example.mita.data.api

import com.example.mita.data.response.LoginResponse
import com.example.mita.data.response.QuestionResponse
import com.example.mita.data.response.RegisterRequest
import com.example.mita.data.response.RegisterResponse
import com.example.mita.data.response.SubmitResponse
import com.example.mita.data.response.TherapyResponse
import com.example.mita.data.response.UserDataResponse
import com.example.mita.viewModel.SubmitAnswerRequest
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("users/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    // Fungsi login tetap sama
    @POST("users/login")
    fun login(@Body requestBody: RequestBody): Call<LoginResponse>
    @GET("users/{token}")
    suspend fun getUserData(
        @Header("Authorization") token: String,
        @Part("Username") username: String
    ): UserDataResponse

    @GET("question/all")
    suspend fun getAllQuestion(
        @Query("Question") question: String
    ): QuestionResponse

    @POST("users/submit")
    suspend fun submitAnswer(
        @Body input: SubmitAnswerRequest,
    ): Response<SubmitResponse>

//    @POST("question/{id}")
//    suspend fun getQuestionId(
//        @Header("Authorization") token: String,
//        @Path("id") accId: String,
//        @Part("Description") description: String
//    ): QuestionResponse
//
    @GET("therapy/all")
    suspend fun getAllTherapy(
        @Query("Therapy") therapy: String
    ):TherapyResponse
//
//    @GET("therapy/{id}")
//    suspend fun getTherapyId(
//        @Header("Authorization") token: String,
//        @Path("id") accId: String,
//        @Part("Description") description: String
//    ): TherapyResponse
}