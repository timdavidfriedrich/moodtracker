package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.Record

sealed interface RecordUiState {
    data object Loading : RecordUiState
    data object Error : RecordUiState
    sealed class Success : RecordUiState {
        data class Day(
            val dayRecord: Record.Day,
        ) : Success()

        data class Moment(
            val momentRecord: Record.Moment,
        ) : Success()
    }
}
