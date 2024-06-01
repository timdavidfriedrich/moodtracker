package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Mood
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toFormattedDayStringWithTime
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordState
import java.time.Instant
import java.util.Date
import de.timdavidfriedrich.moodtracker.common.R as commonR

@Composable
fun TodaysMoodsCard(
    state: RecordState.Success.Day,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(commonR.dimen.padding_medium),
                    end = dimensionResource(commonR.dimen.padding_medium),
                    top = dimensionResource(commonR.dimen.padding_small),
                    bottom = dimensionResource(commonR.dimen.padding_medium),
                ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.todays_moods_card_title),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { onAction(RecordAction.Day.AddMomentRecord) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(
                            R.string.todays_moods_card_add_moment_label
                        ),
                    )
                }
            }
            Column {
                state.record.moments.forEach { moment ->
                    TodaysMoodsCardItem(moment, modifier, onAction)
                }
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
        shape = MaterialTheme.shapes.small,
        onClick = { onAction(RecordAction.Moment.EditMomentRecord(moment)) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(moment.mood?.iconId ?: commonR.drawable.moodie_none),
                contentDescription = stringResource(
                    R.string.mood_score_description,
                    moment.mood?.score ?: 0.0
                ),
                modifier = Modifier.size(dimensionResource(commonR.dimen.icon_size_small)),
            )
            Spacer(Modifier.width(dimensionResource(commonR.dimen.padding_small)))
            Text(
                text = moment.date.toFormattedDayStringWithTime(),
            )
        }
    }
}

@Preview
@Composable
private fun TodaysMoodsCardPreview() {
    MoodTrackerTheme {
        TodaysMoodsCard(
            state = RecordState.Success.Day(
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