package de.timdavidfriedrich.moodtracker.app.ui.navigation.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import de.timdavidfriedrich.moodtracker.common.ui.navigation.Destination

internal val NavHostController.canPopBackStack: Boolean
    get() = currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

internal fun NavHostController.navigateBack() {
    if (canPopBackStack) popBackStack()
}

internal fun NavHostController.navigateTo(destination: Destination) {
    navigate(destination) {
        popUpTo(destination) {
            inclusive = false
        }
    }
}

internal fun NavController.afterNavigationHasFinished(action: () -> Unit) {
    currentBackStackEntry?.lifecycle?.addObserver(
        object : LifecycleEventObserver {
            override fun onStateChanged(
                source: LifecycleOwner,
                event: Lifecycle.Event
            ) {
                if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                    action()
                    source.lifecycle.removeObserver(this)
                }
            }
        }
    )
}