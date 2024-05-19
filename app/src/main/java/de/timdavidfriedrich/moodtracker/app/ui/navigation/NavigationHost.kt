package de.timdavidfriedrich.moodtracker.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarScreen
import de.timdavidfriedrich.moodtracker.calendar.ui.navigation.CalendarDestination
import de.timdavidfriedrich.moodtracker.common.ui.navigation.RecordScreenType
import de.timdavidfriedrich.moodtracker.record.ui.RecordScreen
import de.timdavidfriedrich.moodtracker.record.ui.navigation.RecordDestination

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any = CalendarDestination,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        composable<CalendarDestination> {
            CalendarScreen(
                onAddClick = {
                    navController.navigate(RecordDestination(RecordScreenType.DAY.name))
                }
            )
        }
        composable<RecordDestination> {
            RecordScreen(
                onBackClick = {
                    navController.popBackStackWithFallback(startDestination)
                },
                onAddMomentClick = {
                    navController.navigate(RecordDestination(RecordScreenType.MOMENT.name))
                }
            )
        }
    }
}

private fun NavController.popBackStackWithFallback(startDestination: Any) {
    if (!popBackStack()) {
        navigate(startDestination)
    }
}