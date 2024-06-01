package de.timdavidfriedrich.moodtracker.calendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import de.timdavidfriedrich.moodtracker.calendar.ui.components.CalendarSuccessTopBar
import de.timdavidfriedrich.moodtracker.calendar.ui.components.DetailedCalendar
import de.timdavidfriedrich.moodtracker.calendar.ui.components.OverviewCalendar
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.common.ui.components.ErrorElement
import de.timdavidfriedrich.moodtracker.common.ui.components.LoadingElement

@Composable
fun CalendarScreen(
    onAction: (CalendarAction) -> Unit,
    state: CalendarState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CalendarTopBar(
                state = state,
                onAction = { onAction(it) }
            )
        },
        floatingActionButton = {
            CalendarFloatingActionButton(
                onAction = { onAction(it) }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    start = dimensionResource(R.dimen.padding_large),
                    end = dimensionResource(R.dimen.padding_large),
                ),
        ) {
            when (state) {
                is CalendarState.Loading -> CalendarScreenLoading(modifier)
                is CalendarState.Error -> CalendarScreenError(modifier)
                is CalendarState.Success -> {
                    CalendarScreenSuccess(
                        state = state,
                        onAction = { onAction(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CalendarScreenLoading(
    modifier: Modifier = Modifier,
) {
    LoadingElement(modifier)
}

@Composable
private fun CalendarScreenError(
    modifier: Modifier = Modifier,
) {
    ErrorElement(modifier)
}

@Composable
private fun CalendarScreenSuccess(
    state: CalendarState.Success,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {}
) {
    Column(
        modifier = modifier,
    ) {
        when (state.calendarType) {
            is CalendarType.Overview -> OverviewCalendar(
                state = state,
                onAction = onAction,
            )

            is CalendarType.Detailed -> DetailedCalendar(
                state = state,
                onAction = onAction,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarTopBar(
    state: CalendarState,
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {},
) {
    when (state) {
        is CalendarState.Success -> {
            CalendarSuccessTopBar(
                state = state,
                modifier = modifier,
                onAction = onAction,
            )
        }

        else -> {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun CalendarFloatingActionButton(
    modifier: Modifier = Modifier,
    onAction: (CalendarAction) -> Unit = {},
) {
    LargeFloatingActionButton(
        onClick = { onAction(CalendarAction.AddRecord) },
        modifier = modifier,
    ) {
        Icon(Icons.Rounded.Add, null)
    }
}