package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Mood
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordState
import java.time.Instant
import java.util.Date
import de.timdavidfriedrich.moodtracker.common.R as commonR

@Composable
fun MoodSliderCard(
    state: RecordState.Success.Moment,
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
                text = stringResource(R.string.mood_slider_card_title),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.height(dimensionResource(commonR.dimen.padding_small)))
            MoodSlider(
                state = state,
                onAction = onAction,
            )
        }
    }
}

@Preview
@Composable
private fun MoodSliderCardPreview() {
    MoodTrackerTheme {
        MoodSliderCard(
            state = RecordState.Success.Moment(
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