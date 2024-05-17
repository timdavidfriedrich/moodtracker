package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.common.domain.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import java.time.Instant
import java.time.ZoneId
import java.util.Date

@Composable
fun DetailedCalendarRow(
    dayRecord: Record.Day,
    onAction: (CalendarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val localDate = dayRecord.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAction(CalendarAction.EditRecord(dayRecord)) },
    ) {
        Column {
            Icon(dayRecord.averageMood.icon, dayRecord.averageMood.level.toString())
            Text(text = "${localDate.dayOfMonth}")
            Text(text = localDate.dayOfWeek.name.substring(0, 3))
        }
        Card(
            modifier = Modifier.weight(1f)
        ) {
            Text(dayRecord.moments?.size.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedCalendarRowPreview() {
    MoodTrackerTheme {
        DetailedCalendarRow(
            dayRecord = Record.Day(
                date = Date.from(Instant.now()),
            ),
            onAction = {},
        )
    }
}