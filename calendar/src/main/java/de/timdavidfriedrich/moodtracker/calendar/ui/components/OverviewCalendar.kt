package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarUiState
import de.timdavidfriedrich.moodtracker.common.domain.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import java.time.Instant
import java.util.Date

@Composable
fun OverviewCalendar(
    uiState: CalendarUiState.Success,
    onAction: (CalendarAction) -> Unit = {},
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

@Preview(showBackground = true)
@Composable
private fun OverviewCalendarPreview() {
    MoodTrackerTheme {
        OverviewCalendar(
            uiState = CalendarUiState.Success(
                dayRecords = listOf(
                    Record.Day(
                        date = Date.from(Instant.now()),
                    ),
                    Record.Day(
                        date = Date.from(Instant.now()),
                    ),
                ),
            ),
        )
    }
}