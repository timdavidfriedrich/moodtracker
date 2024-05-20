package de.timdavidfriedrich.moodtracker.app.di

import de.timdavidfriedrich.moodtracker.calendar.di.Koin.calendarModule
import de.timdavidfriedrich.moodtracker.common.di.Koin.commonModule
import de.timdavidfriedrich.moodtracker.record.di.Koin.recordModule

object Koin {
    val modules = listOf(
        commonModule,
        calendarModule,
        recordModule,
    )
}