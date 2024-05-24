package de.timdavidfriedrich.moodtracker.record.data.repositories

import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.DayRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.EmotionLocalMapper
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.MomentRecordLocalMapper
import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.data.extensions.endOfTheDay
import de.timdavidfriedrich.moodtracker.record.data.extensions.startOfTheDay
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import java.util.Date

class RecordRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : RecordRepository {
    override fun getDayRecordById(id: Long): Flow<Record.Day> {
        return localDataSource.getDayRecordWithMomentRecordsById(id).mapNotNull { dayRecord ->
            DayRecordLocalMapper.toModel(dayRecord)
        }
    }

    override suspend fun getDayRecordByDate(date: Date): Record.Day? {
        return DayRecordLocalMapper.toModel(
            localDataSource.getDayRecordWithMomentRecordsByDateRange(
                startDate = date.startOfTheDay(),
                endDate = date.endOfTheDay(),
            )
        )
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
        val dayRecord = getDayRecordByDate(momentRecord.date)
            ?: Record.Day(date = momentRecord.date)

        val momentRecordWithDayRecordId = MomentRecordLocalMapper
            .toEntityWithDayRecordId(momentRecord, dayRecord.id)

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