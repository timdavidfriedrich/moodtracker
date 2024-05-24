package de.timdavidfriedrich.moodtracker.record.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.timdavidfriedrich.moodtracker.common.domain.models.Mood
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.navigation.RecordScreenType
import de.timdavidfriedrich.moodtracker.record.domain.usecases.DeleteDayRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetAllAvailableEmotionsUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetDayRecordByDateUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetDayRecordByIdUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveDayRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveMomentRecordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class RecordViewModel(
    private val getAllAvailableEmotionsUseCase: GetAllAvailableEmotionsUseCase,
    private val getDayRecordByIdUseCase: GetDayRecordByIdUseCase,
    private val getDayRecordByDateUseCase: GetDayRecordByDateUseCase,
    private val saveDayRecordUseCase: SaveDayRecordUseCase,
    private val deleteDayRecordUseCase: DeleteDayRecordUseCase,
    private val saveMomentRecordUseCase: SaveMomentRecordUseCase,
    private val deleteMomentRecordUseCase: DeleteDayRecordUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _uiState = MutableStateFlow<RecordUiState>(RecordUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val recordScreenType: String? = savedStateHandle["recordType"]

    init {
        when (recordScreenType) {
            RecordScreenType.DAY.name -> initDayRecordScreen()
            RecordScreenType.MOMENT.name -> initMomentRecordScreen()
            else -> showMissingRecordTypeError()
        }
        initAvailableEmotions()
    }

    private fun showMissingRecordTypeError() {
        _uiState.value = RecordUiState.Error.RecordTypeIsMissing
    }

    private fun initAvailableEmotions() {
        viewModelScope.launch {
            getAllAvailableEmotionsUseCase()
                .catch { _uiState.value = RecordUiState.Error.Data }
                .collect { emotions ->
                    _uiState.update { currentState ->
                        when (currentState) {
                            is RecordUiState.Success.Day -> {
                                currentState.copy(availableEmotions = emotions)
                            }

                            is RecordUiState.Success.Moment -> {
                                currentState.copy(availableEmotions = emotions)
                            }

                            else -> currentState
                        }
                    }
                }
        }
    }

    private fun initDayRecordScreen() {
        viewModelScope.launch {
            val dayRecord = getDayRecordByDateUseCase(Date.from(Instant.now()))
            if (_uiState.value !is RecordUiState.Success) {
                _uiState.value = RecordUiState.Success.Day(Record.Day())
            }
            _uiState.update {
                (it as RecordUiState.Success.Day).copy(
                    record = dayRecord ?: Record.Day(),
                )
            }
        }
    }

    private fun initMomentRecordScreen() {
        _uiState.value = RecordUiState.Success.Moment(
            record = Record.Moment(),
        )
    }

    fun onAction(action: RecordAction) {
        when (action) {
            is RecordAction.Moment.MoodSliderChange -> updateMoodSlider(action.score)
            is RecordAction.NoteChange -> updateNote(action.note)
            is RecordAction.SaveRecord -> saveCurrentRecord()
            else -> {}
        }
    }

    private fun updateMoodSlider(score: Float) {
        _uiState.update {
            when (it) {
                is RecordUiState.Success.Moment -> {
                    val updatedRecord = it.record.copy(
                        mood = it.record.mood
                            ?.copy(score = score.toDouble())
                            ?: Mood(score = score.toDouble())
                    )
                    it.copy(record = updatedRecord)
                }

                else -> it
            }
        }
    }

    private fun updateNote(note: String) {
        _uiState.update {
            when (it) {
                is RecordUiState.Success.Day -> {
                    val updatedRecord = it.record.copy(note = note)
                    it.copy(record = updatedRecord)
                }

                is RecordUiState.Success.Moment -> {
                    val updatedRecord = it.record.copy(note = note)
                    it.copy(record = updatedRecord)
                }

                else -> it
            }
        }
    }

    private fun saveCurrentRecord() {
        if (uiState.value !is RecordUiState.Success) return
        viewModelScope.launch {
            when (val record = (uiState.value as RecordUiState.Success).record) {
                is Record.Day -> saveDayRecordUseCase(record)
                is Record.Moment -> saveMomentRecordUseCase(record)
            }
        }
    }
}