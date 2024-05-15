package de.timdavidfriedrich.moodtracker.common.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.timdavidfriedrich.moodtracker.common.data.converters.DateConverters
import java.util.Date

@Entity(tableName = "dayRecords")
data class DayRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @TypeConverters(DateConverters::class)
    val date: Date,
    @Embedded(prefix = "moodGraphData")
    val moodGraphData: MoodGraphDataEntity? = null,
    @Embedded(prefix = "songOfTheDay")
    val songOfTheDay: SongEntity? = null,
    val note: String? = null,
)