package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Mood
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date

@Composable
fun MoodSliderCard(
    uiState: RecordUiState.Success.Moment,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Slider(
            value = uiState.record.mood?.score?.toFloat() ?: 0f,
            onValueChange = { onAction(RecordAction.Moment.MoodSliderChange(it)) },
        )
    }
}

@Preview
@Composable
private fun MoodSliderCardPreview() {
    MoodTrackerTheme {
        MoodSliderCard(
            uiState = RecordUiState.Success.Moment(
                record = Record.Moment(
                    date = Date.from(Instant.now()),
                    mood = Mood(
                        score = 0.5,
                    ),
                )
            )
        )
    }
}