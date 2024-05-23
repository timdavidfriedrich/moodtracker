package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size_default)),
                painter = painterResource(dayRecord.averageMood.iconId),
                contentDescription = dayRecord.averageMood.level.toString(),
            )
            Text(
                text = "${localDate.dayOfMonth}",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = localDate.dayOfWeek.name.substring(0, 3),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Spacer(Modifier.width(dimensionResource(R.dimen.padding_default)))
        Card(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
        ) {
            Text(dayRecord.moments.size.toString())
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