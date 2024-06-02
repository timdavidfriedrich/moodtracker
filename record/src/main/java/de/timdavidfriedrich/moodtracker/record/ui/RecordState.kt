package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

sealed interface RecordState {

    data class Navigating(
        val navigationAction: RecordNavigationAction,
    ) : RecordState

    data object Loading : RecordState

    sealed interface Error : RecordState {
        data object RecordTypeIsMissing : Error
        data object Data : Error
    }

    sealed interface Success : RecordState {
        val record: Record
        val availableEmotions: List<Emotion>

        data class Day(
            override val record: Record.Day,
            override val availableEmotions: List<Emotion> = listOf(),
        ) : Success

        data class Moment(
            override val record: Record.Moment,
            override val availableEmotions: List<Emotion> = listOf(),
        ) : Success
    }
}

sealed interface RecordNavigationAction
data class ToMomentRecord(val record: Record.Moment? = null) : RecordNavigationAction
data class ToDayRecord(val record: Record.Day? = null) : RecordNavigationAction
data object Back : RecordNavigationAction
