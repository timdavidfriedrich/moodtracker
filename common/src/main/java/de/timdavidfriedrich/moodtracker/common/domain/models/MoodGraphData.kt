package de.timdavidfriedrich.moodtracker.common.domain.models

data class MoodGraphData(
    val id: Long? = null,
    val morningMood: Mood?,
    val afternoonMood: Mood?,
    val eveningMood: Mood?,
    val nightMood: Mood?,
)
