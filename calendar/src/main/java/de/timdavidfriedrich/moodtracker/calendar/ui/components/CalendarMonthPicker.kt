package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarState
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toMilliseconds
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toYearMonth
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarMonthPicker(
    state: CalendarState.Success,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {},
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = state.month.toMilliseconds(),
        initialDisplayedMonthMillis = state.month.toMilliseconds(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        }
    )

    val selectedDate = datePickerState.selectedDateMillis?.toYearMonth() ?: YearMonth.now()

    DatePickerDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(
                onClick = { onAction(CalendarAction.PickMonth(selectedDate)) },
            ) {
                Text("Okay")
            }
        },
        modifier = modifier,
    ) {
        DatePicker(
            state = datePickerState,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarMonthPickerPreview() {
    MoodTrackerTheme {
        CalendarMonthPicker(CalendarState.Success())
    }
}