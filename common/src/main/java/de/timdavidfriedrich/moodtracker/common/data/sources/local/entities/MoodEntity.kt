package de.timdavidfriedrich.moodtracker.common.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class MoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val score: Double = 0.0,
)