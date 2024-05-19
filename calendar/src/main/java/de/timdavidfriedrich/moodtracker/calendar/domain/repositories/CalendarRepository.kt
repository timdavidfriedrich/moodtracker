package de.timdavidfriedrich.moodtracker.calendar.domain.repositories

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    fun getAllDayRecords(): Flow<List<Record.Day>>
}