package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date
import de.timdavidfriedrich.moodtracker.common.R as commonR

@Composable
fun NoteCard(
    uiState: RecordUiState.Success,
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
                text = stringResource(R.string.note_card_title),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.height(dimensionResource(commonR.dimen.padding_small)))
            TextField(
                shape = MaterialTheme.shapes.small,
                colors = OutlinedTextFieldDefaults.colors().copy(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                ),
                value = uiState.record.note ?: "",
                onValueChange = { onAction(RecordAction.NoteChange(it)) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
    MoodTrackerTheme {
        NoteCard(
            uiState = RecordUiState.Success.Day(
                record = Record.Day(
                    date = Date.from(Instant.now()),
                    note = "Test note 123, yooyoyo",
                ),
            ),
        )
    }
}