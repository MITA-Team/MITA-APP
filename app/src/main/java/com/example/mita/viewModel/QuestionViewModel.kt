package com.example.mita.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mita.data.api.ApiConfig
import com.example.mita.data.response.Input
import com.example.mita.data.response.ListItem
import com.example.mita.data.response.LoginResponse
import com.example.mita.data.response.SubmitResponse
import retrofit2.HttpException


data class SubmitAnswerRequest(val input: Input)

class QuestionViewModel : ViewModel() {

    private val _questions = mutableStateOf<List<ListItem>>(emptyList())
    val questions: State<List<ListItem>> = _questions

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    private val _currentQuestionIndex = mutableIntStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    @SuppressLint("MutableCollectionMutableState")
    private val _answers = mutableStateOf(mutableMapOf<String, Int>())
    val answers: State<Map<String, Int>> = _answers

    private val _questionsAnswers = MutableLiveData<Map<String, Map<Int, Boolean>>>()
    val questionsAnswers: LiveData<Map<String, Map<Int, Boolean>>> get() = _questionsAnswers

    private val _submitResult = MutableLiveData<SubmitResult>()
    val submitResult: LiveData<SubmitResult> get() = _submitResult

    data class SubmitResult(
        val newResultId: String,
        val recommendations: com.example.mita.data.response.Recommendations
    )

    data class Recommendations(
        val image: String,
        val predictionAsd: String,
        val topPredictions: List<TopPrediction>
    )

    data class TopPrediction(
        val alias: String,
        val id: String,
        val probability: String,
        val therapy: String
    )

    suspend fun fetchQuestions() {
        try {
            // Ambil pertanyaan tanpa menggunakan token
            val response = ApiConfig.createApiService().getAllQuestion("your_description")
            _questions.value = response.list
        } catch (e: Exception) {
            // Handle error, misalnya tampilkan pesan kesalahan
            Log.e("QuestionViewModel", "Error fetching questions", e)
        }
    }

    // Metode untuk mengganti pertanyaan dengan ID berikutnya
    fun nextQuestion() {
        val questionSize = _questions.value.size
        if (questionSize > 0) {
            _currentQuestionIndex.intValue = (_currentQuestionIndex.intValue + 1) % questionSize
        }
    }

    suspend fun submitAnswer(isYes: Boolean) {
        try {
            // Menyimpan jawaban sementara
            val currentQuestion = _questions.value.getOrNull(_currentQuestionIndex.intValue)

            currentQuestion?.let { question ->
                // Memperbarui nilai jawaban untuk pertanyaan saat ini
                val currentField = when (_currentQuestionIndex.intValue) {
                    in 0 .. 10 -> "A${_currentQuestionIndex.intValue + 1}"
                    11 -> "Age"
                    12 -> "Sex"
                    13 -> "Jaudience"
                    14 -> "Family_mem_with_ASD"
                    15 -> "Who_completed_the_test"
                    else -> null
                }

                currentField?.let {
                    _answers.value[it] = if (isYes) 1 else 0
                }
            }

            // Jika semua pertanyaan telah dijawab, kirim jawaban ke API
            if (_answers.value.size == _questions.value.size) {
                // Membuat objek model untuk data yang akan dikirim
                val submitAnswerRequest = SubmitAnswerRequest(input = Input(
                    A1 = _answers.value["A1"] ?: 0,
                    A2 = _answers.value["A2"] ?: 0,
                    A3 = _answers.value["A3"] ?: 0,
                    A4 = _answers.value["A4"] ?: 0,
                    A5 = _answers.value["A5"] ?: 0,
                    A6 = _answers.value["A6"] ?: 0,
                    A7 = _answers.value["A7"] ?: 0,
                    A8 = _answers.value["A8"] ?: 0,
                    A9 = _answers.value["A9"] ?: 0,
                    A10 = _answers.value["A10"] ?: 0,
                    Age = _answers.value["Age"] ?: 0,
                    Sex = _answers.value["Sex"] ?: 0,
                    Jaudience = _answers.value["Jaudience"] ?: 0,
                    Family_mem_with_ASD = _answers.value["Family_mem_with_ASD"] ?: 0,
                    Who_completed_the_test = _answers.value["Who_completed_the_test"] ?: 0
                ))

                // Memanggil fungsi API untuk mengirim jawaban
                val response = ApiConfig.createApiService().submitAnswer(submitAnswerRequest)


                // Reset jawaban setelah dikirim
                _answers.value.clear()
            }

            // Pindah ke pertanyaan berikutnya setelah jawaban disimpan
            nextQuestion()
        } catch (e: Exception) {
            // Handle error with more details
            Log.e("QuestionViewModel", "Error submitting answer", e)

            if (e is HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.e("QuestionViewModel", "Error response body: $responseBody")
            }

            _answers.value.clear()
        }
    }

    private fun handleSubmitResponse(response: SubmitResponse) {
        if (response.status == 200) {
            val newResultId = response.data.newResultId
            val recommendations = response.data.recommendations
            _submitResult.value = SubmitResult(newResultId, recommendations)
        } else {
            // Tanggapi kesalahan
            Log.e("QuestionViewModel", "Submit answer failed: ${response.message}")
        }
    }
}
