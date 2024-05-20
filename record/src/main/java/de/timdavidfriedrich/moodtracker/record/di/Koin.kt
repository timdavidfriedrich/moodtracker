package de.timdavidfriedrich.moodtracker.record.di

import de.timdavidfriedrich.moodtracker.record.data.repositories.RecordRepositoryImpl
import de.timdavidfriedrich.moodtracker.record.domain.repositories.RecordRepository
import de.timdavidfriedrich.moodtracker.record.domain.usecases.DeleteDayRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.DeleteMomentRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetAllAvailableEmotionsUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetDayRecordByDateUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetDayRecordByIdUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveDayRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveMomentRecordUseCase
import de.timdavidfriedrich.moodtracker.record.ui.RecordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {
    val recordModule: Module = module {
        viewModel<RecordViewModel> {
            RecordViewModel(
                getAllAvailableEmotionsUseCase = get(),
                getDayRecordByIdUseCase = get(),
                getDayRecordByDateUseCase = get(),
                saveDayRecordUseCase = get(),
                deleteDayRecordUseCase = get(),
                saveMomentRecordUseCase = get(),
                deleteMomentRecordUseCase = get(),
                savedStateHandle = get(),
            )
        }
        single<RecordRepository> { RecordRepositoryImpl(localDataSource = get()) }

        // Use cases
        single<GetDayRecordByIdUseCase> { GetDayRecordByIdUseCase(recordRepository = get()) }
        single<GetDayRecordByDateUseCase> { GetDayRecordByDateUseCase(recordRepository = get()) }
        single<SaveDayRecordUseCase> { SaveDayRecordUseCase(recordRepository = get()) }
        single<DeleteDayRecordUseCase> { DeleteDayRecordUseCase(recordRepository = get()) }
        single<SaveMomentRecordUseCase> { SaveMomentRecordUseCase(recordRepository = get()) }
        single<DeleteMomentRecordUseCase> { DeleteMomentRecordUseCase(recordRepository = get()) }
        single<GetAllAvailableEmotionsUseCase> {
            GetAllAvailableEmotionsUseCase(recordRepository = get())
        }
    }
}