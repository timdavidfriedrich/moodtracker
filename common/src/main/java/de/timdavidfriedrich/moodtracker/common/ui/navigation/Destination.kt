package de.timdavidfriedrich.moodtracker.common.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object Calendar : Destination

    @Serializable
    data class Record(
        val recordScreenType: RecordScreenType,
        val recordTimestamp: Long = -1L,
    ) : Destination
}