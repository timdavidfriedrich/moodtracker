package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState

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
            value = uiState.momentRecord.mood.score.toFloat(),
            onValueChange = { onAction(RecordAction.Moment.MoodSliderChange(it)) },
        )
    }
}