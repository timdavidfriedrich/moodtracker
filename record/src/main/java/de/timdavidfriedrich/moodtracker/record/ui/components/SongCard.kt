package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.domain.models.Song
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordUiState
import de.timdavidfriedrich.moodtracker.common.R as commonR

@Composable
fun SongCard(
    uiState: RecordUiState.Success,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    val song = uiState.record.song

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
                modifier = Modifier.padding(dimensionResource(commonR.dimen.padding_none))
            ) {
                Text(
                    text = stringResource(R.string.song_card_day_title),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.padding(dimensionResource(commonR.dimen.padding_none)),
                    onClick = { onAction(RecordAction.AddCurrentSong) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Refresh,
                        contentDescription = stringResource(R.string.refresh_song_description),
                        modifier = Modifier.padding(dimensionResource(commonR.dimen.padding_none)),
                    )
                }
            }
            Spacer(Modifier.height(dimensionResource(commonR.dimen.padding_small)))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.size(dimensionResource(R.dimen.song_card_image_size)),
                ) { }
                Spacer(Modifier.width(dimensionResource(commonR.dimen.padding_medium)))
                Column {
                    Text(
                        text = song?.title ?: stringResource(R.string.unknown_title_label),
                    )
                    Text(
                        text = song?.album ?: stringResource(R.string.unknown_album_label),
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Text(
                        text = song?.artist ?: stringResource(R.string.unknown_artist_label),
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.bodySmall,
                    )
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