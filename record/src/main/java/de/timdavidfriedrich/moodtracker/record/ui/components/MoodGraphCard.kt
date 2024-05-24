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
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import de.timdavidfriedrich.moodtracker.common.R as commonR

@Composable
fun MoodGraphCard(
    uiState: RecordUiState.Success.Day,
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
                text = stringResource(R.string.todays_mood_graph_card_title),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.height(dimensionResource(commonR.dimen.padding_small)))
        }
    }
}