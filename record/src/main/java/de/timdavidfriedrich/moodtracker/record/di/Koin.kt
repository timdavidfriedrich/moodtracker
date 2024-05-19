package de.timdavidfriedrich.moodtracker.record.di

import de.timdavidfriedrich.moodtracker.record.ui.RecordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {

    val recordModule: Module = module {
        viewModel<RecordViewModel> {
            RecordViewModel(
                savedStateHandle = get(),
            )
        }
    }

}