package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion

sealed interface RecordAction {
    data object BackClick : RecordAction
    data object AddCurrentSong : RecordAction
    data class NoteChange(val note: String) : RecordAction
    data class ToggleEmotion(val emotion: Emotion) : Moment
    data object SaveRecord : RecordAction

    sealed interface Day : RecordAction {
        data object AddMomentRecord : Day
        data object ResetMoodGraph : Day
    }

    sealed interface Moment : RecordAction {
        data class MoodSliderChange(val score: Float) : Moment
    }
}