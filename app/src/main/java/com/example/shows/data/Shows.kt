package com.example.shows.data

import java.util.*

data class Show(
    val id: Int,
    val title: String,
    val location: String,
    val dateTime: String,
    val endDateTime: String,
    val genre: List<String>,
    val logo: String,
    val artists: List<Artist>
)

data class Artist(
    val name: String,
    val website: String,
    val bio: String,
    val photo: String
)