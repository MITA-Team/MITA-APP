package com.example.mita.data.response

data class SubmitResponse(
    val data: DataSubmit,
    val message: String,
    val status: Int
)

data class DataSubmit(
    val newResultId: String,
    val recommendations: Recommendations
)

data class TopPredictionsItem(
    val therapy: String,
    val alias: String,
    val probability: String,
    val id: String
)

data class Recommendations(
    val image: String,
    val topPredictions: List<TopPredictionsItem>,
    val predictionAsd: String
)

data class SubmitRequest(
    val input: Input
)

data class Input(
    val A1: Int,
    val A2: Int,
    val A3: Int,
    val A4: Int,
    val A5: Int,
    val A6: Int,
    val A7: Int,
    val A8: Int,
    val A9: Int,
    val A10: Int,
    val Age: Int,
    val Sex: Int,
    val Jaudience: Int,
    val Family_mem_with_ASD: Int,
    val Who_completed_the_test: Int
)
