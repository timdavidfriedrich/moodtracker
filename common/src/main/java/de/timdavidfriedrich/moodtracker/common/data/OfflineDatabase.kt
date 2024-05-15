package de.timdavidfriedrich.moodtracker.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.timdavidfriedrich.moodtracker.common.data.converters.DateConverters
import de.timdavidfriedrich.moodtracker.common.data.daos.DayRecordDao
import de.timdavidfriedrich.moodtracker.common.data.daos.EmotionDao
import de.timdavidfriedrich.moodtracker.common.data.daos.MomentRecordDao
import de.timdavidfriedrich.moodtracker.common.data.daos.MoodDao
import de.timdavidfriedrich.moodtracker.common.data.daos.MoodGraphDataDao
import de.timdavidfriedrich.moodtracker.common.data.daos.SongDao
import de.timdavidfriedrich.moodtracker.common.data.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.EmotionEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.MoodEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.MoodGraphDataEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.SongEntity

@Database(
    entities = [
        DayRecordEntity::class,
        EmotionEntity::class,
        MomentRecordEntity::class,
        MoodEntity::class,
        MoodGraphDataEntity::class,
        SongEntity::class,
    ],
    version = 1,
)
@TypeConverters(DateConverters::class)
abstract class OfflineDatabase : RoomDatabase() {
    abstract fun dayRecordDao(): DayRecordDao
    abstract fun emotionDao(): EmotionDao
    abstract fun momentRecordDao(): MomentRecordDao
    abstract fun moodDao(): MoodDao
    abstract fun moodGraphDataDao(): MoodGraphDataDao
    abstract fun songDao(): SongDao
}