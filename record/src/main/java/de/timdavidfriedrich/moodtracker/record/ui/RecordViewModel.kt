package de.timdavidfriedrich.moodtracker.record.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import de.timdavidfriedrich.moodtracker.common.domain.Record
import de.timdavidfriedrich.moodtracker.common.ui.navigation.RecordScreenType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecordViewModel(
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