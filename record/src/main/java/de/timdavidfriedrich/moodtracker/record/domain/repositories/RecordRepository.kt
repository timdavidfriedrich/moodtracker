package de.timdavidfriedrich.moodtracker.record.domain.repositories

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface RecordRepository {
    fun getDayRecordById(id: Long): Flow<Record.Day?>
    fun getDayRecordByDate(date: Date): Flow<Record.Day?>
    suspend fun saveDayRecord(dayRecord: Record.Day)
    suspend fun deleteDayRecord(dayRecord: Record.Day)
    fun getMomentRecordByDate(date: Date): Flow<Record.Moment?>
    suspend fun saveMomentRecord(momentRecord: Record.Moment)
    suspend fun deleteMomentRecord(momentRecord: Record.Moment)
    fun getAllAvailableEmotions(): Flow<List<Emotion>>
}