package com.example.mita.data.response

data class QuestionResponse(
	val message: String? = null,
	val list: List<ListItemQuestion?>? = null,
	val status: Int? = null
)

data class ListItemQuestion(
	val data: Data? = null,
	val id: String? = null
)

data class Data(
	val question: String? = null,
	val category: String? = null,
	val type: List<String?>? = null
)

