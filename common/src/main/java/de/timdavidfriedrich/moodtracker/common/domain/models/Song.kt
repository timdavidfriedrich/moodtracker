package de.timdavidfriedrich.moodtracker.common.domain.models

data class Song(
    val id: Long? = null,
    val title: String? = null,
    val album: String? = null,
    val artist: String? = null,
    val coverUrl: String? = null,
    val url: String? = null,
)