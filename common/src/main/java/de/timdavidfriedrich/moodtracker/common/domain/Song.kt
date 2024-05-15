package de.timdavidfriedrich.moodtracker.common.domain

data class Song(
    val title: String? = null,
    val album: String? = null,
    val artist: String? = null,
    val url: String,
)