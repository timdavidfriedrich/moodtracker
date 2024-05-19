package de.timdavidfriedrich.moodtracker.common.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.repositories.CommonRepository

class GetAllAvailableEmotionsUseCase(
    private val commonRepository: CommonRepository,
) {
    operator fun invoke() = commonRepository.getAllAvailableEmotions()
}