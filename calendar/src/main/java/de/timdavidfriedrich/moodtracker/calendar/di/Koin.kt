package de.timdavidfriedrich.moodtracker.calendar.di

import de.timdavidfriedrich.moodtracker.calendar.data.repositories.CalendarRepositoryImpl
import de.timdavidfriedrich.moodtracker.calendar.domain.repositories.CalendarRepository
import de.timdavidfriedrich.moodtracker.calendar.domain.usecases.GetAllDayRecordsUseCase
import de.timdavidfriedrich.moodtracker.calendar.ui.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {
    val calendarModule: Module = module {
        viewModel<CalendarViewModel> { CalendarViewModel(getAllDayRecordsUseCase = get()) }
        single<CalendarRepository> { CalendarRepositoryImpl(localDataSource = get()) }

        // Use cases
        single<GetAllDayRecordsUseCase> { GetAllDayRecordsUseCase(calendarRepository = get()) }
    }
}