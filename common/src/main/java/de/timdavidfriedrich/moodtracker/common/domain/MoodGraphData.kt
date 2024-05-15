package de.timdavidfriedrich.moodtracker.common.domain

data class MoodGraphData(
    val morningMood: Mood?,
    val afternoonMood: Mood?,
    val eveningMood: Mood?,
    val nightMood: Mood?,
)
