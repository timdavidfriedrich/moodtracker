package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordState
import de.timdavidfriedrich.moodtracker.common.R as commonR

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmotionsCard(
    state: RecordState.Success,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(commonR.dimen.padding_medium)),
        ) {
            Text(
                text = stringResource(R.string.emotions_card_title),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.height(dimensionResource(commonR.dimen.padding_small)))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(commonR.dimen.padding_small)
                ),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(commonR.dimen.padding_small)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                state.availableEmotions.forEach {
                    EmotionCardItem(
                        emotion = it,
                        onAction = onAction,
                    )
                }
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
    Card(
        shape = MaterialTheme.shapes.small,
        onClick = { onAction(RecordAction.ToggleEmotion(emotion)) },
        modifier = modifier.width(dimensionResource(commonR.dimen.grid_column_size_extra_large)),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(commonR.dimen.padding_extra_small))
        ) {
            Text(
                text = emotion.icon,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.height(dimensionResource(commonR.dimen.padding_extra_small)))
            Text(
                text = emotion.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    hyphens = Hyphens.Auto,
                    lineBreak = LineBreak.Paragraph,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun EmotionsCardPreview() {
    EmotionsCard(
        state = RecordState.Success.Day(
            record = Record.Day(),
            availableEmotions = listOf(
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
}