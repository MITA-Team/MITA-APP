package com.example.mita.data.response

data class TherapyResponse(
	val message: String? = null,
	val list: List<ListItemTherapy?>? = null,
	val status: Int? = null
)

data class DataTherapy(
	val therapy: Therapy? = null,
	val category: String? = null,
	val type: List<String?>? = null
)

data class Therapy(
	val name: String? = null,
	val description: List<String?>? = null
)

data class ListItemTherapy(
	val data: DataTherapy? = null,
	val id: String? = null
)

