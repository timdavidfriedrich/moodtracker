package de.timdavidfriedrich.moodtracker.common.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String? = null,
    val album: String? = null,
    val artist: String? = null,
    val coverUrl: String? = null,
    val url: String? = null,
)
