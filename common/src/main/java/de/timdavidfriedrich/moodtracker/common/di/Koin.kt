package de.timdavidfriedrich.moodtracker.common.di

import android.app.Application
import androidx.room.Room
import de.timdavidfriedrich.moodtracker.common.data.repositories.CommonRepositoryImpl
import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSourceImpl
import de.timdavidfriedrich.moodtracker.common.data.sources.local.LocalDatabase
import de.timdavidfriedrich.moodtracker.common.data.sources.local.converters.DateConverters
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.DayRecordDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.EmotionDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MomentRecordDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MoodDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.MoodGraphDataDao
import de.timdavidfriedrich.moodtracker.common.data.sources.local.daos.SongDao
import de.timdavidfriedrich.moodtracker.common.domain.repositories.CommonRepository
import de.timdavidfriedrich.moodtracker.common.domain.usecases.GetAllAvailableEmotionsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {
    private fun provideDatabase(application: Application): LocalDatabase {
        return Room
            .databaseBuilder(application, LocalDatabase::class.java, "local_database")
            .addTypeConverter(DateConverters())
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun provideDayRecordDao(database: LocalDatabase) = database.dayRecordDao()
    private fun provideEmotionDao(database: LocalDatabase) = database.emotionDao()
    private fun provideMomentRecordDao(database: LocalDatabase) = database.momentRecordDao()
    private fun provideMoodDao(database: LocalDatabase) = database.moodDao()
    private fun provideMoodGraphDataDao(database: LocalDatabase) = database.moodGraphDataDao()
    private fun provideSongDao(database: LocalDatabase) = database.songDao()

    val localDatabaseModule = module {
        single<LocalDatabase> { provideDatabase(application = get()) }
        single<LocalDataSource> { LocalDataSourceImpl(database = get()) }
        single<DayRecordDao> { provideDayRecordDao(database = get()) }
        single<EmotionDao> { provideEmotionDao(database = get()) }
        single<MomentRecordDao> { provideMomentRecordDao(database = get()) }
        single<MoodDao> { provideMoodDao(database = get()) }
        single<MoodGraphDataDao> { provideMoodGraphDataDao(database = get()) }
        single<SongDao> { provideSongDao(database = get()) }
    }

    val commonModule: Module = module {
        single<CommonRepository> { CommonRepositoryImpl(localDataSource = get()) }

        // Use cases
        single<GetAllAvailableEmotionsUseCase> {
            GetAllAvailableEmotionsUseCase(commonRepository = get())
        }
    }
}