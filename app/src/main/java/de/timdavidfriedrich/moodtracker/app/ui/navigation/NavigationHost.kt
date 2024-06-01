package de.timdavidfriedrich.moodtracker.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarScreen
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarState
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarViewModel
import de.timdavidfriedrich.moodtracker.common.ui.navigation.Destination
import de.timdavidfriedrich.moodtracker.common.ui.navigation.RecordScreenType
import de.timdavidfriedrich.moodtracker.record.ui.RecordScreen
import de.timdavidfriedrich.moodtracker.record.ui.RecordState
import de.timdavidfriedrich.moodtracker.record.ui.RecordViewModel
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
                if (value !is CalendarState.Success) return@LaunchedEffect

                when {
                    value.clickedOnAddRecord -> {
                        navController.navigateTo(Destination.Record(RecordScreenType.DAY))
                    }
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
        ) {
            val recordScreenType = it.toRoute<Destination.Record>().recordScreenType
            val viewModel = koinViewModel<RecordViewModel> { parametersOf(recordScreenType) }
            val state = viewModel.state.collectAsState()

            LaunchedEffect(key1 = state.value) {
                val value = state.value
                if (value !is RecordState.Success) return@LaunchedEffect

                when {
                    value.clickedBack -> {
                        navController.navigateTo(Destination.Calendar)
                    }

                    value is RecordState.Success.Day && value.clickedOnAddMoment -> {
                        navController.navigateTo(Destination.Record(RecordScreenType.MOMENT))
                    }
                }
            }

            RecordScreen(
                onAction = viewModel::onAction,
                state = state.value,
            )
        }
    }
}

private val NavHostController.canPopBackStack: Boolean
    get() = currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

private fun NavHostController.navigateBack() {
    if (canPopBackStack) popBackStack()
}

private fun NavHostController.navigateTo(destination: Destination) {
    navigate(destination) {
        popUpTo(destination) {
            inclusive = false
        }
    }
}