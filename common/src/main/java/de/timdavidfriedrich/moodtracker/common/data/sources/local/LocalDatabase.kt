package de.timdavidfriedrich.moodtracker.common.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.DateConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.DayRecordDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.EmotionDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MomentRecordDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MoodDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MoodGraphDataDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.SongDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodGraphDataEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.SongEntity

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
abstract class LocalDatabase : RoomDatabase() {
    abstract fun dayRecordDao(): DayRecordDao
    abstract fun emotionDao(): EmotionDao
    abstract fun momentRecordDao(): MomentRecordDao
    abstract fun moodDao(): MoodDao
    abstract fun moodGraphDataDao(): MoodGraphDataDao
    abstract fun songDao(): SongDao
}