package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository

class DeleteDayRecordUseCase(
    private val recordRepository: RecordRepository,
) {
    suspend operator fun invoke(record: Record.Day, deleteMomentRecords: Boolean = true) {
        if (deleteMomentRecords) {
            for (moment in record.moments) {
                recordRepository.deleteMomentRecord(moment)
            }
        }
        recordRepository.deleteDayRecord(record)
    }
}