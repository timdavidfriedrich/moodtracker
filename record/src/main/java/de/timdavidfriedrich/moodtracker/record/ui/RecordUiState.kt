package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

sealed interface RecordUiState {
    data object Loading : RecordUiState
    sealed interface Error : RecordUiState {
        data object RecordTypeIsMissing : Error
        data object Data : Error
    }

    sealed class Success : RecordUiState {
        abstract val record: Record
        abstract val availableEmotions: List<Emotion>

        data class Day(
            override val record: Record.Day,
            override val availableEmotions: List<Emotion> = listOf(),
        ) : Success()

        data class Moment(
            override val record: Record.Moment,
            override val availableEmotions: List<Emotion> = listOf(),
        ) : Success()
    }
}
