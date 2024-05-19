package de.timdavidfriedrich.moodtracker.record.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.domain.usecases.GetAllAvailableEmotionsUseCase
import de.timdavidfriedrich.moodtracker.common.ui.navigation.RecordScreenType
import de.timdavidfriedrich.moodtracker.record.domain.usecases.DeleteDayRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetDayRecordByIdUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveDayRecordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecordViewModel(
    private val getAllAvailableEmotionsUseCase: GetAllAvailableEmotionsUseCase,
    private val getDayRecordByIdUseCase: GetDayRecordByIdUseCase,
    private val saveDayRecordUseCase: SaveDayRecordUseCase,
    private val deleteDayRecordUseCase: DeleteDayRecordUseCase,
    private val saveMomentRecordUseCase: SaveDayRecordUseCase,
    private val deleteMomentRecordUseCase: DeleteDayRecordUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _uiState = MutableStateFlow<RecordUiState>(RecordUiState.Loading)
    val uiState = _uiState.asStateFlow()

    // TODO: Replace string
    private val recordScreenType: String? = savedStateHandle["recordType"]

    init {
        when (recordScreenType) {
            RecordScreenType.DAY.name -> initDayRecordScreen()
            RecordScreenType.MOMENT.name -> initMomentRecordScreen()
            else -> showMissingRecordTypeError()
        }
    }

    private fun showMissingRecordTypeError() {
        _uiState.value = RecordUiState.Error.RecordTypeIsMissing
    }

    private fun initDayRecordScreen() {
        _uiState.value = RecordUiState.Success.Day(
            record = Record.Day(),
        )
    }

    private fun initMomentRecordScreen() {
        _uiState.value = RecordUiState.Success.Moment(
            record = Record.Moment(),
        )
    }

    fun onAction(action: RecordAction) {
    }
}