package de.timdavidfriedrich.moodtracker.app.di

import de.timdavidfriedrich.moodtracker.calendar.di.Koin.calendarModule
import de.timdavidfriedrich.moodtracker.common.di.Koin.databaseModule
import de.timdavidfriedrich.moodtracker.record.di.Koin.recordModule

object Koin {
    val modules = listOf(
        databaseModule,
        calendarModule,
        recordModule,
    )
}