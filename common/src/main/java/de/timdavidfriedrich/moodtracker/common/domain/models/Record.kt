package de.timdavidfriedrich.moodtracker.common.domain.models

import java.time.Instant
import java.util.Date

sealed class Record {
    abstract val id: Long?
    abstract val date: Date
    abstract val note: String?
    abstract val song: Song?
    abstract val emotions: List<Emotion>

    data class Day(
        override val id: Long? = null,
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
                        .mapNotNull { it.mood?.score }
                        .average()
                        .takeIf { !it.isNaN() }
                        ?: 0.0
                )
            }
    }

    data class Moment(
        override val id: Long? = null,
        override val date: Date = Date.from(Instant.now()),
        override val emotions: List<Emotion> = listOf(),
        override val note: String? = null,
        override val song: Song? = null,
        val mood: Mood? = Mood(score = 0.0),
    ) : Record()

}
