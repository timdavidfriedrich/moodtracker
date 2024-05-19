package de.timdavidfriedrich.moodtracker.common.domain

import java.time.Instant
import java.util.Date

sealed class Record {
    abstract val date: Date
    abstract val note: String?
    abstract val song: Song?
    abstract val emotions: List<Emotion>

    data class Day(
        override val date: Date = Date.from(Instant.now()),
        override val emotions: List<Emotion> = listOf(),
        override val note: String? = null,
        override val song: Song? = null,
        val moodGraphData: MoodGraphData? = null,
        val moments: List<Moment> = listOf(),
    ) : Record() {
        val averageMood: Mood
            get() {
                return Mood(
                    score = moments
                        .map { it.mood.score }
                        .average()
                        .takeIf { !it.isNaN() }
                        ?: 0.0
                )
            }
    }

    data class Moment(
        override val date: Date = Date.from(Instant.now()),
        override val emotions: List<Emotion> = listOf(),
        override val note: String? = null,
        override val song: Song? = null,
        val mood: Mood = Mood(0.0),
    ) : Record()

}
