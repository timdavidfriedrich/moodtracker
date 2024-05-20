package de.timdavidfriedrich.moodtracker.calendar.ui

import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import java.time.YearMonth

sealed interface CalendarAction {
    data object AddRecord : CalendarAction
    data class EditRecord(val dayRecord: Record.Day) : CalendarAction
    data object DismissMonthPicker : CalendarAction
    data object JumpToToday : CalendarAction
    data object OpenMonthPicker : CalendarAction
    data class PickMonth(val month: YearMonth) : CalendarAction
    data object SwitchCalendarType : CalendarAction
    data object SettingsClick : CalendarAction
}