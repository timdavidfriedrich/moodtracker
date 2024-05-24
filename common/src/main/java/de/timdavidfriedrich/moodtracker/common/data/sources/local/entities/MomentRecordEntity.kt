package de.timdavidfriedrich.moodtracker.common.data.sources.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.DateConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.EmotionConverters
import java.util.Date

@Entity(tableName = "momentRecords")
data class MomentRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @TypeConverters(DateConverters::class)
    val date: Date,
    @Embedded(prefix = "mood")
    val mood: MoodEntity? = null,
    val dayRecordId: Long? = null,
    @Embedded(prefix = "song")
    val song: SongEntity? = null,
    val note: String? = null,
    @TypeConverters(EmotionConverters::class)
    val emotions: List<EmotionEntity>? = null,
)
