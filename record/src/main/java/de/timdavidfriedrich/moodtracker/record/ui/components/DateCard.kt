package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date

@Composable
fun DateCard(
    uiState: RecordUiState.Success,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Rounded.CalendarToday, null)
            when (uiState) {
                is RecordUiState.Success.Day -> {
                    Text(text = "${uiState.dayRecord.date}")
                }

                is RecordUiState.Success.Moment -> {
                    Text(text = "${uiState.momentRecord.date}")
                }
            }
        }
    }
}

@Preview
@Composable
private fun DateCardPreview() {
    MoodTrackerTheme {
        DateCard(
            uiState = RecordUiState.Success.Day(
                dayRecord = Record.Day(
                    date = Date.from(Instant.now()),
                )
            )
        )
    }
}