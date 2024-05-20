package de.timdavidfriedrich.moodtracker.record.domain.repositories

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface RecordRepository {
    fun getDayRecordById(id: Long): Flow<Record.Day>
    suspend fun getDayRecordByDate(date: Date): Record.Day?
    suspend fun saveDayRecord(dayRecord: Record.Day)
    suspend fun deleteDayRecord(dayRecord: Record.Day)
    suspend fun saveMomentRecord(momentRecord: Record.Moment)
    suspend fun deleteMomentRecord(momentRecord: Record.Moment)
    fun getAllAvailableEmotions(): Flow<List<Emotion>>
}