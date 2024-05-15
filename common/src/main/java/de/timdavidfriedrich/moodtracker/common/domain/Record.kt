package de.timdavidfriedrich.moodtracker.common.domain

import java.util.Date

sealed interface Record {
    data class Day(
        val date: Date,
        val moodGraphData: MoodGraphData? = null,
        val moments: List<Moment>? = null,
        val songOfTheDay: Song? = null,
        val note: String? = null,
    ) : Record {
        val averageMood: Mood
            get() = Mood(moments?.map { it.mood.score }?.average() ?: 0.0)
    }

    data class Moment(
        val date: Date,
        val mood: Mood,
        val emotions: List<Emotion>? = null,
        val songOfTheMoment: Song? = null,
        val note: String? = null,
    ) : Record

}
