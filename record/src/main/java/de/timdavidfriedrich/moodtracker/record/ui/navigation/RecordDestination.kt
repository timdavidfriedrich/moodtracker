package de.timdavidfriedrich.moodtracker.record.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data class RecordDestination(
    val recordType: String?
)