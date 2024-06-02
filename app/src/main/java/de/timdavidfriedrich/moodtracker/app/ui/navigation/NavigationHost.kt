package de.timdavidfriedrich.moodtracker.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import de.timdavidfriedrich.moodtracker.app.ui.navigation.extensions.afterNavigationHasFinished
import de.timdavidfriedrich.moodtracker.app.ui.navigation.extensions.navigateBack
import de.timdavidfriedrich.moodtracker.app.ui.navigation.extensions.navigateTo
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarScreen
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarState
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarViewModel
import de.timdavidfriedrich.moodtracker.calendar.ui.ToRecord
import de.timdavidfriedrich.moodtracker.common.ui.navigation.Destination
import de.timdavidfriedrich.moodtracker.common.ui.navigation.RecordScreenType
import de.timdavidfriedrich.moodtracker.record.ui.Back
import de.timdavidfriedrich.moodtracker.record.ui.RecordScreen
import de.timdavidfriedrich.moodtracker.record.ui.RecordState
import de.timdavidfriedrich.moodtracker.record.ui.RecordViewModel
import de.timdavidfriedrich.moodtracker.record.ui.ToMomentRecord
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any = Destination.Calendar,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        composable<Destination.Calendar> {
            val viewModel = koinViewModel<CalendarViewModel>()
            val state = viewModel.state.collectAsState()

            LaunchedEffect(key1 = state.value) {
                val value = state.value
                if (value !is CalendarState.Navigating) return@LaunchedEffect

                when (val action = value.navigationAction) {
                    is ToRecord -> {
                        navController.navigateTo(
                            Destination.Record(
                                recordScreenType = RecordScreenType.DAY,
                                recordTimestamp = action.record?.date?.time ?: -1L,
                            )
                        )
                    }

                    else -> {} // do nothing
                }

                navController.afterNavigationHasFinished {
                    value.previousState?.let { viewModel.loadPreviousState(it) }
                }
            }

            CalendarScreen(
                onAction = viewModel::onAction,
                state = state.value,
            )
        }
        composable<Destination.Record>(
            typeMap = mapOf(
                typeOf<RecordScreenType>() to NavType.EnumType(RecordScreenType::class.java),
            ),
        ) { backStackEntry ->
            val arguments = backStackEntry.toRoute<Destination.Record>()
            val viewModel = koinViewModel<RecordViewModel> {
                parametersOf(
                    arguments.recordScreenType,
                    arguments.recordTimestamp.takeUnless { it == -1L },
                )
            }
            val state = viewModel.state.collectAsState()

            LaunchedEffect(key1 = state.value) {
                val value = state.value
                if (value !is RecordState.Navigating) return@LaunchedEffect

                when (val action = value.navigationAction) {
                    is Back -> {
                        navController.navigateBack()
                    }

                    is ToMomentRecord -> {
                        navController.navigateTo(
                            destination = Destination.Record(
                                recordScreenType = RecordScreenType.MOMENT,
                                recordTimestamp = action.record?.date?.time ?: -1L,
                            )
                        )
                    }

                    else -> {} // do nothing
                }

                navController.afterNavigationHasFinished {
                    value.previousState?.let { viewModel.loadPreviousState(it) }
                }
            }

            RecordScreen(
                onAction = viewModel::onAction,
                state = state.value,
            )
        }
    }
}