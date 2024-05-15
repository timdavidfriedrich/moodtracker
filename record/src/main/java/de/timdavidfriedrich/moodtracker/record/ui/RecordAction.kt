package de.timdavidfriedrich.moodtracker.record.ui

import de.timdavidfriedrich.moodtracker.common.domain.Emotion

sealed interface RecordAction {
    data object BackClick : RecordAction
    data object AddCurrentSong : RecordAction
    data class NoteChange(val note: String) : RecordAction
    sealed interface Day : RecordAction {
        data object AddMomentRecord : Day
        data object ResetMoodGraph : Day
    }

    sealed interface Moment : RecordAction {
        data class MoodSliderChange(val score: Float) : Moment
        data class AddEmotion(val emotion: Emotion) : Moment
        data class RemoveEmotion(val emotion: Emotion) : Moment
    }
}