package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Date

class GetOrCreateDayRecordByDateUseCase(
    private val recordRepository: RecordRepository,
) {
    suspend operator fun invoke(date: Date?): Flow<Record.Day> {
        return when (date) {
            null -> flowOf(Record.Day())
            else -> flowOf(recordRepository.getDayRecordByDate(date) ?: Record.Day())
        }
    }
}