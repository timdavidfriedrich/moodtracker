package de.timdavidfriedrich.moodtracker.common.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotions")
data class EmotionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val icon: String,
    val name: String,
)