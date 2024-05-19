package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmotionsCard(
    uiState: RecordUiState.Success,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(dimensionResource(R.dimen.grid_column_size_default)),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.record.emotions, key = { it }) {
                EmotionCardItem(
                    emotion = it,
                    onAction = onAction,
                )
            }
        }
    }
}

@Composable
private fun EmotionCardItem(
    emotion: Emotion,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onAction(RecordAction.ToggleEmotion(emotion)) },
    ) {
        Text(emotion.icon)
        Text(emotion.name)
    }
}

@Preview
@Composable
private fun EmotionsCardPreview() {
    EmotionsCard(
        uiState = RecordUiState.Success.Day(
            record = Record.Day(
                date = Date.from(Instant.now()),
                emotions = listOf(
                    Emotion(icon = "☁️", name = "emotion"),
                    Emotion(icon = "☂️", name = "misunderstood"),
                    Emotion(icon = "🌪️", name = "furious"),
                    Emotion(icon = "🎀", name = "emotion"),
                    Emotion(icon = "🛌", name = "eepy"),
                    Emotion(icon = "💖", name = "emotion"),
                    Emotion(icon = "🩹", name = "emotion"),
                    Emotion(icon = "🕯️", name = "emotion"),
                    Emotion(icon = "🌼", name = "emotion"),
                    Emotion(icon = "✨", name = "emotion"),
                    Emotion(icon = "🪩", name = "disco"),
                    Emotion(icon = "🐢", name = "emotion"),
                    Emotion(icon = "🎲", name = "emotion"),
                    Emotion(icon = "🪁", name = "levitating"),
                ),
            )
        )
    )
}