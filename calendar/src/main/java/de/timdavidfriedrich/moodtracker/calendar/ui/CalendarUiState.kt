package de.timdavidfriedrich.moodtracker.calendar.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import java.time.YearMonth

sealed interface CalendarState {
    data object Loading : CalendarState
    data object Error : CalendarState
    data class Success(
        val isMonthPickerVisible: Boolean = false,
        val calendarType: CalendarType = CalendarType.Overview,
        val month: YearMonth = YearMonth.now(),
        val isCurrentMonthSelected: Boolean = true,
        val dayRecords: List<Record.Day> = listOf(),
        val clickedOnAddRecord: Boolean = false,
    ) : CalendarState
}

sealed interface CalendarType {
    data object Overview : CalendarType
    data object Detailed : CalendarType
}