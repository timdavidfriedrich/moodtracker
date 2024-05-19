package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository

class DeleteMomentRecordUseCase(
    private val recordRepository: RecordRepository,
) {
    suspend operator fun invoke(record: Record.Moment) = recordRepository.deleteMomentRecord(record)
}