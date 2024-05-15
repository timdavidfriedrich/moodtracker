package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.common.domain.Record
import java.time.ZoneId

@Composable
fun OverviewCalendarItem(
    dayRecord: Record.Day,
    onAction: (CalendarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val localDate = dayRecord.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    Column(
        modifier = modifier.clickable { onAction(CalendarAction.EditRecord(dayRecord)) },
    ) {
        Icon(dayRecord.averageMood.icon, dayRecord.averageMood.level.toString())
        Text(text = "${localDate.dayOfMonth}")
    }
}