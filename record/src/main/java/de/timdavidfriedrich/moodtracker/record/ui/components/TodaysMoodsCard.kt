package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Mood
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date

@Composable
fun TodaysMoodsCard(
    uiState: RecordUiState.Success.Day,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Text(text = stringResource(R.string.todays_moods_card_title))
        Column {
            uiState.record.moments.forEach { moment ->
                TodaysMoodsCardItem(moment, modifier, onAction)
            }
        }
        Button(
            onClick = { onAction(RecordAction.Day.AddMomentRecord) },
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(Icons.Rounded.Add, null)
                Text(text = stringResource(R.string.todays_moods_card_add_moment_label))
            }
        }
    }
}

@Composable
private fun TodaysMoodsCardItem(
    moment: Record.Moment,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Text(text = "${moment.date}")
    }
}

@Preview
@Composable
private fun TodaysMoodsCardPreview() {
    MoodTrackerTheme {
        TodaysMoodsCard(
            uiState = RecordUiState.Success.Day(
                record = Record.Day(
                    date = Date.from(Instant.now()),
                    moments = listOf(
                        Record.Moment(
                            date = Date.from(Instant.now()),
                            mood = Mood(
                                score = 0.5,
                            ),
                        ),
                        Record.Moment(
                            date = Date.from(Instant.now()),
                            mood = Mood(
                                score = 0.5,
                            ),
                        ),
                    ),
                )
            )
        )
    }
}