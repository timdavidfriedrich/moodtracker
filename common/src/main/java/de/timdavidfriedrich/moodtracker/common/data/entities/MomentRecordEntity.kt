package de.timdavidfriedrich.moodtracker.common.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.timdavidfriedrich.moodtracker.common.data.converters.DateConverters
import java.util.Date

@Entity(tableName = "momentRecords")
data class MomentRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @TypeConverters(DateConverters::class)
    val date: Date,
    @Embedded(prefix = "mood")
    val mood: MoodEntity? = null,
    val dayRecordId: Long? = null,
    @Embedded(prefix = "songOfTheMoment")
    val songOfTheMoment: SongEntity? = null,
    val note: String? = null,
)
