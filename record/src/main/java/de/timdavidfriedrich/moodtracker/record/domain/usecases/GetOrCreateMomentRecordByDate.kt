package de.timdavidfriedrich.moodtracker.record.domain.usecases

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.Date

class GetOrCreateMomentRecordByDate(
    private val recordRepository: RecordRepository,
) {
    operator fun invoke(date: Date?): Flow<Record.Moment> {
        return when (date) {
            null -> flowOf(Record.Moment())
            else -> recordRepository.getMomentRecordByDate(date).map { it ?: Record.Moment() }
        }
    }
}