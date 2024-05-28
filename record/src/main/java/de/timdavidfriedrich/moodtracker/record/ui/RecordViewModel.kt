package de.timdavidfriedrich.moodtracker.record.ui

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class RecordViewModel(
    recordScreenType: RecordScreenType,
    private val getAllAvailableEmotionsUseCase: GetAllAvailableEmotionsUseCase,
    private val getDayRecordByIdUseCase: GetDayRecordByIdUseCase,
    private val getDayRecordByDateUseCase: GetDayRecordByDateUseCase,
    private val saveDayRecordUseCase: SaveDayRecordUseCase,
    private val deleteDayRecordUseCase: DeleteDayRecordUseCase,
    private val saveMomentRecordUseCase: SaveMomentRecordUseCase,
    private val deleteMomentRecordUseCase: DeleteDayRecordUseCase,
) : ViewModel() {

    var state = MutableStateFlow<RecordState>(RecordState.Loading)
        private set

    init {
        when (recordScreenType) {
            RecordScreenType.DAY -> initDayRecordScreen()
            RecordScreenType.MOMENT -> initMomentRecordScreen()
            else -> showMissingRecordTypeError()
        }
        initAvailableEmotions()
    }

    private fun showMissingRecordTypeError() {
        state.value = RecordState.Error.RecordTypeIsMissing
    }

    private fun initAvailableEmotions() {
        viewModelScope.launch {
            getAllAvailableEmotionsUseCase()
                .catch { state.value = RecordState.Error.Data }
                .collect { emotions ->
                    state.update { currentState ->
                        when (currentState) {
                            is RecordState.Success.Day -> {
                                currentState.copy(availableEmotions = emotions)
                            }

                            is RecordState.Success.Moment -> {
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
            if (state.value !is RecordState.Success) {
                state.value = RecordState.Success.Day(Record.Day())
            }
            state.update {
                (it as RecordState.Success.Day).copy(
                    record = dayRecord ?: Record.Day(),
                )
            }
        }
    }

    private fun initMomentRecordScreen() {
        state.value = RecordState.Success.Moment(
            record = Record.Moment(),
        )
    }

    fun onAction(action: RecordAction) {
        when (action) {
            is RecordAction.Moment.MoodSliderChange -> updateMoodSlider(action.score)
            is RecordAction.NoteChange -> updateNote(action.note)
            is RecordAction.SaveRecord -> saveCurrentRecord()
            is RecordAction.BackClick -> navigateBack()
            is RecordAction.Day.AddMomentRecord -> navigateToMomentRecord()
            else -> {}
        }
    }

    private fun navigateBack() {
        state.update {
            when (it) {
                is RecordState.Success.Day -> it.copy(clickedBack = true)
                is RecordState.Success.Moment -> it.copy(clickedBack = true)
                else -> it
            }
        }
    }

    private fun navigateToMomentRecord() {
        state.update {
            when (it) {
                is RecordState.Success.Day -> it.copy(clickedOnAddMoment = true)
                else -> it
            }
        }
    }

    private fun updateMoodSlider(score: Float) {
        state.update {
            when (it) {
                is RecordState.Success.Moment -> {
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
        state.update {
            when (it) {
                is RecordState.Success.Day -> {
                    val updatedRecord = it.record.copy(note = note)
                    it.copy(record = updatedRecord)
                }

                is RecordState.Success.Moment -> {
                    val updatedRecord = it.record.copy(note = note)
                    it.copy(record = updatedRecord)
                }

                else -> it
            }
        }
    }

    private fun saveCurrentRecord() {
        if (state.value !is RecordState.Success) return
        viewModelScope.launch {
            when (val record = (state.value as RecordState.Success).record) {
                is Record.Day -> saveDayRecordUseCase(record)
                is Record.Moment -> saveMomentRecordUseCase(record)
            }
        }
        navigateBack()
    }
}