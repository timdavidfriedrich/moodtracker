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
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.common.domain.Record
import java.time.ZoneId

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