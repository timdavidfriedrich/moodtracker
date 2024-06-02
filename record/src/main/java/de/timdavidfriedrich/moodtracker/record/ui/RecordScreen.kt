package de.timdavidfriedrich.moodtracker.record.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import de.timdavidfriedrich.moodtracker.common.ui.components.ErrorElement
import de.timdavidfriedrich.moodtracker.common.ui.components.LoadingElement
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.components.DateCard
import de.timdavidfriedrich.moodtracker.record.ui.components.EmotionsCard
import de.timdavidfriedrich.moodtracker.record.ui.components.MoodGraphCard
import de.timdavidfriedrich.moodtracker.record.ui.components.MoodSliderCard
import de.timdavidfriedrich.moodtracker.record.ui.components.NoteCard
import de.timdavidfriedrich.moodtracker.record.ui.components.SongCard
import de.timdavidfriedrich.moodtracker.record.ui.components.TodaysMoodsCard
import de.timdavidfriedrich.moodtracker.common.R as commonR

@Composable
fun RecordScreen(
    onAction: (RecordAction) -> Unit,
    state: RecordState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            RecordTopBar(
                onAction = { onAction(it) }
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        when (state) {
            is RecordState.Navigating -> LoadingElement(modifier.padding(innerPadding))
            is RecordState.Loading -> LoadingElement(modifier.padding(innerPadding))
            is RecordState.Error -> ErrorElement(modifier.padding(innerPadding))
            is RecordState.Success -> {
                RecordScreenSuccess(
                    state = state,
                    modifier = modifier
                        .padding(innerPadding)
                        .padding(
                            start = dimensionResource(commonR.dimen.padding_medium),
                            end = dimensionResource(commonR.dimen.padding_medium),
                        ),
                    onAction = { onAction(it) }
                )
            }
        }
    }
}

@Composable
private fun RecordScreenSuccess(
    state: RecordState.Success,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(commonR.dimen.padding_medium)),
        modifier = modifier,
    ) {
        item {
            DateCard(state)
        }

        if (state is RecordState.Success.Day) {
            item {
                MoodGraphCard(state, Modifier, onAction)
            }
        }

        if (state is RecordState.Success.Moment) {
            item {
                MoodSliderCard(state, Modifier, onAction)
            }
        }

        item {
            EmotionsCard(state, Modifier, onAction)
        }

        if (state is RecordState.Success.Day) {
            item {
                TodaysMoodsCard(state, Modifier, onAction)
            }
        }

        item {
            SongCard(state, Modifier, onAction)
        }

        item {
            NoteCard(state, Modifier, onAction)
        }

        item {
            Button(
                onClick = { onAction(RecordAction.SaveRecord) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(id = R.string.moment_record_save_label))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecordTopBar(
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.day_record_title)) },
        navigationIcon = {
            IconButton(
                onClick = { onAction(RecordAction.BackClick) }
            ) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
            }
        },
        modifier = modifier,
    )
}