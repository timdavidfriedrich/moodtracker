package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import java.util.Date

class GetDayRecordByDateUseCase(
    private val recordRepository: RecordRepository,
) {
    suspend operator fun invoke(date: Date): Record.Day? {
        return recordRepository.getDayRecordByDate(date)
    }
}
