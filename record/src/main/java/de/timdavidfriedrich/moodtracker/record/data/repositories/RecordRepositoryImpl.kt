package de.timdavidfriedrich.moodtracker.record.data.repositories

import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.DayRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.EmotionLocalMapper
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.MomentRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.data.extensions.endOfTheDay
import de.timdavidfriedrich.moodtracker.record.data.extensions.endOfTheMinute
import de.timdavidfriedrich.moodtracker.record.data.extensions.startOfTheDay
import de.timdavidfriedrich.moodtracker.record.data.extensions.startOfTheMinute
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Date

class RecordRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : RecordRepository {
    override fun getDayRecordById(id: Long): Flow<Record.Day?> {
        return localDataSource.getDayRecordWithMomentRecordsById(id)
            .map { DayRecordLocalMapper.toModel(it) }
    }

    override fun getDayRecordByDate(date: Date): Flow<Record.Day?> {
        return localDataSource.getDayRecordWithMomentRecordsByDateRange(
            startDate = date.startOfTheDay(),
            endDate = date.endOfTheDay(),
        ).map { DayRecordLocalMapper.toModel(it) }
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

    override fun getMomentRecordByDate(date: Date): Flow<Record.Moment?> {
        return localDataSource.getMomentRecordByDateRange(
            startDate = date.startOfTheMinute(),
            endDate = date.endOfTheMinute(),
        ).map { MomentRecordLocalMapper.toModel(it) }
    }

    override suspend fun saveMomentRecord(momentRecord: Record.Moment) {
        val dayRecord = getDayRecordByDate(momentRecord.date)

        val momentRecordWithDayRecordId = MomentRecordLocalMapper
            .toEntityWithDayRecordId(momentRecord, dayRecord.first()?.id)

        if (momentRecordWithDayRecordId.id == null) {
            localDataSource.insertMomentRecord(momentRecordWithDayRecordId)
        } else {
            localDataSource.updateMomentRecord(momentRecordWithDayRecordId)
        }
    }

    override suspend fun deleteMomentRecord(momentRecord: Record.Moment) {
        val momentRecordEntity = MomentRecordLocalMapper.toEntity(momentRecord)
        momentRecordEntity?.let {
            localDataSource.deleteMomentRecord(it)
        }
    }

    override fun getAllAvailableEmotions(): Flow<List<Emotion>> {
        return localDataSource.getAllEmotions().map { emotions ->
            emotions.mapNotNull { EmotionLocalMapper.toModel(it) }
        }
    }
}