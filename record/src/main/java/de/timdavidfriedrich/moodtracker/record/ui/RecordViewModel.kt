package de.timdavidfriedrich.moodtracker.record.ui

import androidx.lifecycle.ViewModel
import de.timdavidfriedrich.moodtracker.common.domain.Record
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.util.Date

class RecordViewModel : ViewModel() {

    private var _uiState = MutableStateFlow<RecordUiState>(RecordUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = RecordUiState.Success.Day(
            dayRecord = Record.Day(
                date = Date.from(Instant.now()),
                note = "I'm feeling great!"
            ),
        )
    }

    fun onAction(action: RecordAction) {
    }
}