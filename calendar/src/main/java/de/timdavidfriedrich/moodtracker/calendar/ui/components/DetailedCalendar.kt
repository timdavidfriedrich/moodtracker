package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarUiState
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import java.time.Instant
import java.util.Date

@Composable
fun DetailedCalendar(
    uiState: CalendarUiState.Success,
    onAction: (CalendarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        //modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        uiState.dayRecords.let {
            items(it) { dayRecord ->
                DetailedCalendarRow(dayRecord, onAction)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedCalendarPreview() {
    MoodTrackerTheme {
        DetailedCalendar(
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
            onAction = {},
        )
    }
}