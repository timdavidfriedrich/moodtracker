package de.timdavidfriedrich.moodtracker.app

import android.app.Application
import de.timdavidfriedrich.moodtracker.app.di.Koin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoodTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoodTrackerApplication)
            modules(Koin.modules)
        }
    }
}