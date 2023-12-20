package com.example.mita.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mita.data.api.ApiConfig
import com.example.mita.data.response.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.awaitResponse

class AuthViewModel : ViewModel() {
    private val apiService = ApiConfig.createApiService()

    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> get() = _registrationResult

    private val _loginResult = MutableStateFlow(false)
    val loginResult: StateFlow<Boolean> get() = _loginResult

    private val isLoggedIn = MutableLiveData<Boolean>()


    fun isLoggedIn(): LiveData<Boolean> {
        return isLoggedIn
    }


    fun register(
        registerRequest: RegisterRequest
    ) = viewModelScope.launch {
        try {
            val response = apiService.register(registerRequest)
            println("Response: $response")
            // Jika respons berhasil, tetapkan nilai sesuai dengan kebutuhan
            _registrationResult.value = true // atau sesuai dengan respons yang benar
        } catch (e: Exception) {
            println("Error: $e")
            _registrationResult.value = false
        }
    }

    suspend fun login(identifier: String, password: String): Boolean {
        try {
            val json = JSONObject()
            json.put("identifier", identifier)
            json.put("password", password)

            val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

            val response = apiService.login(requestBody).awaitResponse()

            if (response.isSuccessful) {
                Log.d("AuthViewModel", "Login successful") // Log message when login is successful
            }else{
                Log.e("AuthViewModel", "Login failed: ${response.errorBody()?.string()}")
            }

            return response.isSuccessful
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Login failed", e) // Log error if login fails
            return false
        }
    }
}