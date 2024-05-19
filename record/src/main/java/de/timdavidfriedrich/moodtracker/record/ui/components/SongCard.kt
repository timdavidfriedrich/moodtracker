package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.domain.models.Song
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState

@Composable
fun SongCard(
    uiState: RecordUiState.Success,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    val song = uiState.record.song

    Card(
        onClick = { onAction(RecordAction.AddCurrentSong) },
        modifier = modifier,
    ) {
        Column {
            Text(stringResource(R.string.song_card_day_title))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                    contentDescription = null,
                )
                Column {
                    Text(song?.title ?: stringResource(R.string.unknown_title_label))
                    Text(song?.album ?: stringResource(R.string.unknown_album_label))
                    Text(song?.artist ?: stringResource(R.string.unknown_artist_label))
                }
            }
        }
    }
}

@Preview
@Composable
private fun SongCardPreview() {
    SongCard(
        uiState = RecordUiState.Success.Day(
            record = Record.Day(
                date = java.util.Date(),
                song = Song(
                    title = "Song title",
                    album = "Album title",
                    artist = "Artist name",
                    url = "https://example.com",
                ),
            ),
        ),
    )
}