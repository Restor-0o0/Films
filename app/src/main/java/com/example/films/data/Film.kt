package com.example.films.data

data class Film(
    val  id: Int,
    val localized_name: String,
    val name: String,
    val year: String,
    val rating: Float,
    val image_url: String,
    val description: String,
    val genres: List<String>
)

