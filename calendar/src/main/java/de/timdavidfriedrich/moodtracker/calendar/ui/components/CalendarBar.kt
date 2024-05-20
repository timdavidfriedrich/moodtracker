package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.ViewAgenda
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarType
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarUiState
import de.timdavidfriedrich.moodtracker.calendar.ui.extensions.toFormattedString
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme

@Composable
fun CalendarBar(
    uiState: CalendarUiState.Success,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        TextButton(
            onClick = { onAction(CalendarAction.OpenMonthPicker) }
        ) {
            Text(uiState.month.toFormattedString())
        }
        Spacer(Modifier.weight(1f))
        if (!uiState.isCurrentMonthSelected) {
            IconButton(
                onClick = { onAction(CalendarAction.JumpToToday) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.CalendarToday,
                    contentDescription = "Jump to today",
                )
            }
        }
        IconButton(
            onClick = { onAction(CalendarAction.SwitchCalendarType) }
        ) {
            when (uiState.calendarType) {
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
    }
    if (uiState.isMonthPickerVisible) {
        CalendarMonthPicker(uiState, modifier, onAction)
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarBarPreview() {
    MoodTrackerTheme {
        CalendarBar(CalendarUiState.Success())
    }
}