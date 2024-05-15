package de.timdavidfriedrich.moodtracker.common.di

import android.app.Application
import androidx.room.Room
import de.timdavidfriedrich.moodtracker.common.data.OfflineDatabase
import de.timdavidfriedrich.moodtracker.common.data.converters.DateConverters
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {

    private fun provideDatabase(application: Application): OfflineDatabase {
        return Room
            .databaseBuilder(application, OfflineDatabase::class.java, "offline_database")
            .addTypeConverter(DateConverters::class)
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun provideDayRecordDao(database: OfflineDatabase) = database.dayRecordDao()
    private fun provideEmotionDao(database: OfflineDatabase) = database.emotionDao()
    private fun provideMomentRecordDao(database: OfflineDatabase) = database.momentRecordDao()
    private fun provideMoodDao(database: OfflineDatabase) = database.moodDao()
    private fun provideMoodGraphDataDao(database: OfflineDatabase) = database.moodGraphDataDao()
    private fun provideSongDao(database: OfflineDatabase) = database.songDao()

    val databaseModule: Module = module {
        single { provideDatabase(get()) }
        single { provideEmotionDao(get()) }
        single { provideMoodDao(get()) }
        single { provideMoodGraphDataDao(get()) }
        single { provideDayRecordDao(get()) }
        single { provideMomentRecordDao(get()) }
        single { provideSongDao(get()) }
    }

}