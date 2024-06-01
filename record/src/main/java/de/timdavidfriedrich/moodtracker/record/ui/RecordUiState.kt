package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

sealed interface RecordState {
    data object Loading : RecordState
    sealed interface Error : RecordState {
        data object RecordTypeIsMissing : Error
        data object Data : Error
    }

    sealed class Success : RecordState {
        abstract val record: Record
        abstract val availableEmotions: List<Emotion>
        abstract val clickedBack: Boolean

        data class Day(
            override val record: Record.Day,
            override val availableEmotions: List<Emotion> = listOf(),
            override val clickedBack: Boolean = false,
            val clickedOnAddMoment: Boolean = false,
        ) : Success()

        data class Moment(
            override val record: Record.Moment,
            override val availableEmotions: List<Emotion> = listOf(),
            override val clickedBack: Boolean = false,
        ) : Success()
    }
}
