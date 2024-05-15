package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarUiState

@Composable
fun OverviewCalendar(
    uiState: CalendarUiState.Success,
    onAction: (CalendarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row {
        uiState.dayRecords.forEach { dayRecord ->
            OverviewCalendarItem(
                dayRecord = dayRecord,
                onAction = onAction,
            )
        }
    }
}