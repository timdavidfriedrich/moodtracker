package de.timdavidfriedrich.moodtracker.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.timdavidfriedrich.moodtracker.app.di.Koin
import de.timdavidfriedrich.moodtracker.common.ui.theme.MoodTrackerTheme
import org.koin.compose.KoinApplication

@Composable
fun MoodTrackerApp(
    navController: NavHostController = rememberNavController()
) {
    MoodTrackerTheme {
        NavigationHost(navController = navController)
    }
}

@Preview
@Composable
fun MoodTrackerAppPreview() {
    KoinApplication(
        application = { modules(Koin.modules) }
    ) {
        MoodTrackerTheme {
            MoodTrackerApp()
        }
    }
}