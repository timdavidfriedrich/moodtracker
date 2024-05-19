package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository

class SaveDayRecordUseCase(
    private val recordRepository: RecordRepository,
) {
    suspend operator fun invoke(record: Record.Day) = recordRepository.saveDayRecord(record)
}