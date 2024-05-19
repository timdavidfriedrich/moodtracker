package de.timdavidfriedrich.moodtracker.common.data.sources.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.DateConverters
import java.util.Date

@Entity(tableName = "dayRecords")
data class DayRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @TypeConverters(DateConverters::class)
    val date: Date,
    @Embedded(prefix = "moodGraphData")
    val moodGraphData: MoodGraphDataEntity? = null,
    @Embedded(prefix = "song")
    val song: SongEntity? = null,
    val note: String? = null,
)