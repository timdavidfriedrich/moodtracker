package de.timdavidfriedrich.moodtracker.calendar.data.repositories

import de.timdavidfriedrich.moodtracker.calendar.domain.repositories.CalendarRepository
import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.DayRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalendarRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : CalendarRepository {
    override fun getAllDayRecords(): Flow<List<Record.Day>> {
        return localDataSource.getAllDayRecordsWithMomentRecords().map { dayRecords ->
            dayRecords.mapNotNull { DayRecordLocalMapper.toModel(it) }
        }
    }
}