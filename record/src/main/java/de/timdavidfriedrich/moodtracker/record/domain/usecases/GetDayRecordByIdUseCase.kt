package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository

class GetDayRecordByIdUseCase(
    private val recordRepository: RecordRepository,
) {
    operator fun invoke(id: Long) = recordRepository.getDayRecordById(id)
}