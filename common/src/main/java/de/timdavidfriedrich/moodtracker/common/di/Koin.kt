package de.timdavidfriedrich.moodtracker.common.di

import android.app.Application
import androidx.room.Room
import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSourceImpl
import de.timdavidfriedrich.moodtracker.common.data.sources.local.LocalDatabase
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.DateConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.EmotionConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.DayRecordDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.EmotionDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MomentRecordDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MoodDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MoodGraphDataDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.SongDao
import org.koin.dsl.module

object Koin {
    private fun provideDatabase(application: Application): LocalDatabase {
        return Room
            .databaseBuilder(application, LocalDatabase::class.java, "local_database")
            .createFromAsset("database/local_database.db")
            .addTypeConverter(DateConverters())
            .addTypeConverter(EmotionConverters())
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun provideDayRecordDao(database: LocalDatabase) = database.dayRecordDao()
    private fun provideEmotionDao(database: LocalDatabase) = database.emotionDao()
    private fun provideMomentRecordDao(database: LocalDatabase) = database.momentRecordDao()
    private fun provideMoodDao(database: LocalDatabase) = database.moodDao()
    private fun provideMoodGraphDataDao(database: LocalDatabase) = database.moodGraphDataDao()
    private fun provideSongDao(database: LocalDatabase) = database.songDao()

    val commonModule = module {
        single<LocalDatabase> { provideDatabase(application = get()) }
        single<LocalDataSource> { LocalDataSourceImpl(database = get()) }
        single<DayRecordDao> { provideDayRecordDao(database = get()) }
        single<EmotionDao> { provideEmotionDao(database = get()) }
        single<MomentRecordDao> { provideMomentRecordDao(database = get()) }
        single<MoodDao> { provideMoodDao(database = get()) }
        single<MoodGraphDataDao> { provideMoodGraphDataDao(database = get()) }
        single<SongDao> { provideSongDao(database = get()) }
    }
}