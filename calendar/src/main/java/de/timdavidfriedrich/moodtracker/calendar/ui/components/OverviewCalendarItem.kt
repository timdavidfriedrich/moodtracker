package de.timdavidfriedrich.moodtracker.calendar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarAction
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import java.time.Instant
import java.time.ZoneId
import java.util.Date

@Composable
fun OverviewCalendarItem(
    dayRecord: Record.Day,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {},
) {
    val localDate = dayRecord.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Transparent,
        ),
        onClick = { onAction(CalendarAction.EditRecord(dayRecord)) },
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium)),
                painter = painterResource(dayRecord.averageMood.iconId),
                contentDescription = dayRecord.averageMood.level.toString(),
            )
            Text(text = "${localDate.dayOfMonth}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OverviewCalendarItemPreview() {
    MoodTrackerTheme {
        OverviewCalendarItem(
            dayRecord = Record.Day(
                date = Date.from(Instant.now()),
            ),
        )
    }
}