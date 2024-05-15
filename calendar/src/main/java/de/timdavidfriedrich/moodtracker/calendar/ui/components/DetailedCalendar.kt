package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarUiState

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