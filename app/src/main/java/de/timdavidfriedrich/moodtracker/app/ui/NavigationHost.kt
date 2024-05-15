package de.timdavidfriedrich.moodtracker.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarScreen
import de.timdavidfriedrich.moodtracker.calendar.ui.navigation.CalendarDestination
import de.timdavidfriedrich.moodtracker.common.ui.navigation.NavigationDestination
import de.timdavidfriedrich.moodtracker.record.ui.RecordScreen
import de.timdavidfriedrich.moodtracker.record.ui.navigation.RecordDestination

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: NavigationDestination = CalendarDestination,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination.route,
    ) {
        composable(
            route = CalendarDestination.route,
        ) {
            CalendarScreen(
                onAddClick = {
                    navController.navigate(RecordDestination.route)
                }
            )
        }
        composable(
            route = RecordDestination.route,
        ) {
            RecordScreen(
                onBackClick = {
                    navController.popBackStack(CalendarDestination.route, false)
                }
            )
        }
    }
}