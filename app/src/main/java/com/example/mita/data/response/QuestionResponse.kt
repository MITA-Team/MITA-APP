package com.example.mita.data.response

data class QuestionResponse(
	val message: String,
	val list: List<ListItem>,
	val status: Int
)

data class ListItem(
	val data: DataQuestion,
	val id: String
)

data class DataQuestion(
	val question: String,
	val category: String,
	val type: List<String>
)

