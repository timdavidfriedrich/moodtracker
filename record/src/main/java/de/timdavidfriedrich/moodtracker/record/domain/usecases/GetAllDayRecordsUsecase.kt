package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository

class GetAllAvailableEmotionsUseCase(
    private val recordRepository: RecordRepository,
) {
    operator fun invoke() = recordRepository.getAllAvailableEmotions()
}