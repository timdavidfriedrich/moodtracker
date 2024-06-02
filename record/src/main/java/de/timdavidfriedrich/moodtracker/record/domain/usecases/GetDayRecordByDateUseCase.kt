package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class GetDayRecordByDateUseCase(
    private val recordRepository: RecordRepository,
) {
    operator fun invoke(date: Date): Flow<Record.Day?> {
        return recordRepository.getDayRecordByDate(date)
    }
}
