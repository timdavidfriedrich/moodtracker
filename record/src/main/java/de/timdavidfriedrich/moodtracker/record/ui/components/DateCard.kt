package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toFormattedDayString
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toFormattedDayStringWithTime
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date

@Composable
fun DateCard(
    uiState: RecordUiState.Success,
    modifier: Modifier = Modifier,
) {
    val formattedDate = when (uiState) {
        is RecordUiState.Success.Day -> uiState.record.date.toFormattedDayString()
        is RecordUiState.Success.Moment -> uiState.record.date.toFormattedDayStringWithTime()
    }
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
        ) {
            Icon(Icons.Rounded.CalendarToday, null)
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_medium)))
            Text(text = formattedDate)
        }
    }
}

@Preview
@Composable
private fun DateCardPreview() {
    MoodTrackerTheme {
        DateCard(
            uiState = RecordUiState.Success.Day(
                record = Record.Day(
                    date = Date.from(Instant.now()),
                )
            )
        )
    }
}