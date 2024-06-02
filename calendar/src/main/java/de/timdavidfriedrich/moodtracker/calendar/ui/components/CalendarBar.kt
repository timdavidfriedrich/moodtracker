package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.ViewAgenda
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarState
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarType
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toFormattedString
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTopBar(
    state: CalendarState.Success,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.clickable { onAction(CalendarAction.OpenMonthPicker) },
                text = state.month.toFormattedString(),
            )
        },
        actions = {
            IconButton(
                onClick = { onAction(CalendarAction.SwitchCalendarType) }
            ) {
                when (state.calendarType) {
                    is CalendarType.Detailed -> {
                        Icon(
                            imageVector = Icons.Rounded.ViewAgenda,
                            contentDescription = "Change calendar type to overview",
                        )
                    }

                    is CalendarType.Overview -> {
                        Icon(
                            imageVector = Icons.Rounded.GridView,
                            contentDescription = "Change calendar type to detailed",
                        )
                    }
                }
            }
        },
        modifier = modifier,
    )
    if (state.isMonthPickerVisible) {
        CalendarMonthPicker(state, modifier, onAction)
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarBarPreview() {
    MoodTrackerTheme {
        CalendarTopBar(CalendarState.Success())
    }
}