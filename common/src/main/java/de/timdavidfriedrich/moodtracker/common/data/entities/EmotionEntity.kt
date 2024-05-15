package de.timdavidfriedrich.moodtracker.common.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotions")
data class EmotionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val icon: String,
    val name: String,
    val momentRecordId: Long? = null,
)