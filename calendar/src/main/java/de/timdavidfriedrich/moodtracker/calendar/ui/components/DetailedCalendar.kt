package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarState
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import java.time.Instant
import java.util.Date

@Composable
fun DetailedCalendar(
    state: CalendarState.Success,
    onAction: (CalendarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        //modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        items(state.dayRecords) { dayRecord ->
            DetailedCalendarRow(dayRecord, onAction)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedCalendarPreview() {
    MoodTrackerTheme {
        DetailedCalendar(
            state = CalendarState.Success(
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