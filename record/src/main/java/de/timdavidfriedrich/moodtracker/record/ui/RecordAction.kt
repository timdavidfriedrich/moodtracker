package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

sealed interface RecordAction {
    data object BackClick : RecordAction
    data object AddCurrentSong : RecordAction
    data class NoteChange(val note: String) : RecordAction
    data class ToggleEmotion(val emotion: Emotion) : Moment
    data object SaveRecord : RecordAction
    data object RequestDeleteRecord : RecordAction
    data object CancelDeleteRecord : RecordAction
    data object DeleteRecord : RecordAction

    sealed interface Day : RecordAction {
        data object AddMomentRecord : Day
        data object ResetMoodGraph : Day
    }

    sealed interface Moment : RecordAction {
        data class EditMomentRecord(val moment: Record.Moment) : Moment
        data class MoodSliderChange(val score: Float) : Moment
    }
}