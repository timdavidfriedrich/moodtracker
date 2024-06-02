package de.timdavidfriedrich.moodtracker.calendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import de.timdavidfriedrich.moodtracker.calendar.ui.components.CalendarTopBar
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
            if (state is CalendarState.Success) {
                CalendarTopBar(
                    state = state,
                    onAction = { onAction(it) }
                )
            }
        },
        floatingActionButton = {
            CalendarFloatingActionButton(
                onAction = { onAction(it) }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        when (state) {
            is CalendarState.Navigating -> LoadingElement(modifier.padding(innerPadding))
            is CalendarState.Loading -> LoadingElement(modifier.padding(innerPadding))
            is CalendarState.Error -> ErrorElement(modifier.padding(innerPadding))
            is CalendarState.Success -> {
                CalendarScreenSuccess(
                    state = state,
                    modifier = modifier
                        .padding(innerPadding)
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium),
                        ),
                    onAction = { onAction(it) },
                )
            }
        }
    }
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