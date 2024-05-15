package de.timdavidfriedrich.moodtracker.common.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String? = null,
    val album: String? = null,
    val artist: String? = null,
    val url: String,
)
