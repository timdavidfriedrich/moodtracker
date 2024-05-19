package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Record

sealed interface RecordUiState {
    data object Loading : RecordUiState
    sealed interface Error : RecordUiState {
        data object RecordTypeIsMissing : Error
    }

    sealed class Success : RecordUiState {
        abstract val record: Record

        data class Day(
            override val record: Record.Day,
        ) : Success()

        data class Moment(
            override val record: Record.Moment,
        ) : Success()
    }
}
