package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import java.time.Instant
import java.util.Date

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
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.note_card_title))
            TextField(
                value = uiState.record.note ?: "",
                onValueChange = { onAction(RecordAction.NoteChange(it)) },
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