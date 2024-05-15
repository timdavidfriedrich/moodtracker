package de.timdavidfriedrich.moodtracker.calendar.ui

import de.timdavidfriedrich.moodtracker.common.domain.Record
import java.time.YearMonth

sealed interface CalendarUiState {
    data object Loading : CalendarUiState
    data class Success(
        val isMonthPickerVisible: Boolean = false,
        val calendarType: CalendarType = CalendarType.Overview,
        val month: YearMonth = YearMonth.now(),
        val isCurrentMonthSelected: Boolean = true,
        val dayRecords: List<Record.Day> = listOf(),
    ) : CalendarUiState
}

sealed interface CalendarType {
    data object Overview : CalendarType
    data object Detailed : CalendarType
}