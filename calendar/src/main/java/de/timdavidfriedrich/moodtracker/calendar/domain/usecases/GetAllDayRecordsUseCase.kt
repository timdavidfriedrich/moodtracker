package de.timdavidfriedrich.moodtracker.calendar.domain.usecases

import de.timdavidfriedrich.moodtracker.calendar.domain.repositories.CalendarRepository
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import kotlinx.coroutines.flow.Flow

class GetAllDayRecordsUseCase(
    private val calendarRepository: CalendarRepository,
) {
    operator fun invoke(): Flow<List<Record.Day>> = calendarRepository.getAllDayRecords()
}