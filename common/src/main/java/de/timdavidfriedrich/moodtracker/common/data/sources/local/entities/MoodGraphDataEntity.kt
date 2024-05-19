package de.timdavidfriedrich.moodtracker.common.data.sources.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moodGraphDataList")
data class MoodGraphDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @Embedded("morningMood")
    val morningMood: MoodEntity?,
    @Embedded("afternoonMood")
    val afternoonMood: MoodEntity?,
    @Embedded("eveningMood")
    val eveningMood: MoodEntity?,
    @Embedded("nightMood")
    val nightMood: MoodEntity?,
)
