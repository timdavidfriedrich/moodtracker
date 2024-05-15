package de.timdavidfriedrich.moodtracker.record.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecordViewModel : ViewModel() {

    private var _uiState = MutableStateFlow<RecordUiState>(RecordUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onAction(action: RecordAction) {
        when (action) {
            RecordAction.BackClick -> {}
        }
    }
}