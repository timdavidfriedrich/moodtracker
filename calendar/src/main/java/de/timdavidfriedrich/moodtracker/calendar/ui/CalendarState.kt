package de.timdavidfriedrich.moodtracker.calendar.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import java.time.YearMonth

sealed interface CalendarState {

    data class Navigating(
        val navigationAction: CalendarNavigationState
    ) : CalendarState

    data object Loading : CalendarState

    data object Error : CalendarState

    data class Success(
        val isMonthPickerVisible: Boolean = false,
        val calendarType: CalendarType = CalendarType.Overview,
        val month: YearMonth = YearMonth.now(),
        val isCurrentMonthSelected: Boolean = true,
        val dayRecords: List<Record.Day> = listOf(),
    ) : CalendarState
}

sealed interface CalendarType {
    data object Overview : CalendarType
    data object Detailed : CalendarType
}

sealed interface CalendarNavigationState
data class ToRecord(val record: Record? = null) : CalendarNavigationState