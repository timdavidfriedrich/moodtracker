package de.timdavidfriedrich.moodtracker.record.data.repositories

import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.DayRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.MomentRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class RecordRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : RecordRepository {
    override fun getDayRecordById(id: Long): Flow<Record.Day> {
        return localDataSource.getDayRecordWithMomentRecordsById(id).mapNotNull { dayRecord ->
            DayRecordLocalMapper.toModel(dayRecord)
        }
    }

    override suspend fun saveDayRecord(dayRecord: Record.Day) {
        val dayRecordWithMomentRecords = DayRecordLocalMapper.toEntity(dayRecord)
        dayRecordWithMomentRecords?.let {
            if (dayRecord.id == null) {
                localDataSource.insertDayRecord(it.dayRecord)
            } else {
                localDataSource.updateDayRecord(it.dayRecord)
            }
        }
    }

    override suspend fun deleteDayRecord(dayRecord: Record.Day) {
        val dayRecordWithMomentRecords = DayRecordLocalMapper.toEntity(dayRecord)
        dayRecordWithMomentRecords?.let {
            localDataSource.deleteDayRecord(it.dayRecord)
        }
    }

    override suspend fun saveMomentRecord(momentRecord: Record.Moment) {
        val momentRecordEntity = MomentRecordLocalMapper.toEntity(momentRecord)
        momentRecordEntity?.let {
            if (momentRecord.id == null) {
                localDataSource.insertMomentRecord(it.momentRecord)
            } else {
                localDataSource.updateMomentRecord(it.momentRecord)
            }
        }
    }

    override suspend fun deleteMomentRecord(momentRecord: Record.Moment) {
        val momentRecordEntity = MomentRecordLocalMapper.toEntity(momentRecord)
        momentRecordEntity?.let {
            localDataSource.deleteMomentRecord(it.momentRecord)
        }
    }
}