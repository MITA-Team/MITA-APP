package com.example.mita.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mita.data.api.ApiConfig
import com.example.mita.data.api.ApiService
import com.example.mita.data.response.TherapyResponse
import kotlinx.coroutines.launch

class TherapyViewModel : ViewModel() {

    private val apiService = ApiConfig.createApiService()

    private val _therapies = MutableLiveData<TherapyResponse>()
    val therapies: LiveData<TherapyResponse> = _therapies

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun fetchTherapies(therapy: String) {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val response = apiService.getAllTherapy(therapy)
                _therapies.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
