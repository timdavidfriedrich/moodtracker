package de.timdavidfriedrich.moodtracker.record.ui

sealed interface RecordAction {
    data object BackClick : RecordAction
}