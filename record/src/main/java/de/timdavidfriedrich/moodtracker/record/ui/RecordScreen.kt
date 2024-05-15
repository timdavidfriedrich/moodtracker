package de.timdavidfriedrich.moodtracker.record.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.components.DateCard
import de.timdavidfriedrich.moodtracker.record.ui.components.MoodSliderCard
import de.timdavidfriedrich.moodtracker.record.ui.components.TodaysMoodsCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordViewModel = koinViewModel<RecordViewModel>(),
    onBackClick: () -> Unit = {},
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RecordTopBar(
                onAction = { onBackClick() }
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (val value = uiState.value) {
                is RecordUiState.Loading -> RecordScreenLoading(modifier)
                is RecordUiState.Error -> RecordScreenError(modifier)
                is RecordUiState.Success -> {
                    RecordScreenSuccess(
                        uiState = value,
                        modifier = modifier,
                        onAction = {
                            if (it is RecordAction.BackClick) {
                                onBackClick()
                            } else {
                                viewModel.onAction(it)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RecordScreenLoading(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {

    }
}

@Composable
private fun RecordScreenError(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {

    }
}

@Composable
private fun RecordScreenSuccess(
    uiState: RecordUiState.Success,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        DateCard(uiState)
        if (uiState is RecordUiState.Success.Day) {
            TodaysMoodsCard(uiState, modifier, onAction)
        }
        if (uiState is RecordUiState.Success.Moment) {
            MoodSliderCard(uiState, modifier, onAction)
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