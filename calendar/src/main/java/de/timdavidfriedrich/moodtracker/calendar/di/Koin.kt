package de.timdavidfriedrich.moodtracker.calendar.di

import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {

    val calendarModule: Module = module {
        viewModel<CalendarViewModel> { CalendarViewModel() }
    }

}