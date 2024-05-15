package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.Record

sealed interface RecordUiState {
    data object Loading : RecordUiState
    data object Error : RecordUiState
    data class Success(
        val type: RecordType,
        val record: Record,
    ) : RecordUiState
}

sealed interface RecordType {
    data object Day : RecordType
    data object Moment : RecordType
}
