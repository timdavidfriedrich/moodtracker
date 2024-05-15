package de.timdavidfriedrich.moodtracker.calendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import de.timdavidfriedrich.moodtracker.calendar.ui.components.CalendarBar
import de.timdavidfriedrich.moodtracker.calendar.ui.components.DetailedCalendar
import de.timdavidfriedrich.moodtracker.calendar.ui.components.OverviewCalendar
import de.timdavidfriedrich.moodtracker.common.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = koinViewModel<CalendarViewModel>(),
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { CalendarTopBar() },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (val value = uiState.value) {
                is CalendarUiState.Loading -> {
                    CalendarScreenLoading(value, modifier)
                }

                is CalendarUiState.Success -> {
                    CalendarScreenSuccess(
                        uiState = value,
                        modifier = modifier,
                        onAction = { viewModel.onAction(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarScreenLoading(
    uiState: CalendarUiState.Loading,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {

    }
}

@Composable
fun CalendarScreenSuccess(
    uiState: CalendarUiState.Success,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {}
) {
    Column(
        modifier = modifier,
    ) {
        CalendarBar(uiState, modifier, onAction)
        when (uiState.calendarType) {
            is CalendarType.Overview -> OverviewCalendar(
                uiState = uiState,
                onAction = onAction,
            )

            is CalendarType.Detailed -> DetailedCalendar(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier,
    )
}