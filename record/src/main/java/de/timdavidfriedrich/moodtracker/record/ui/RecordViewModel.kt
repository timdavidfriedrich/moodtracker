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
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetOrCreateDayRecordByDateUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.GetOrCreateMomentRecordByDate
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveDayRecordUseCase
import de.timdavidfriedrich.moodtracker.record.domain.usecases.SaveMomentRecordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class RecordViewModel(
    recordScreenType: RecordScreenType,
    recordTimestamp: Long? = null,
    private val getAllAvailableEmotionsUseCase: GetAllAvailableEmotionsUseCase,
    private val getDayRecordByIdUseCase: GetDayRecordByIdUseCase,
    private val getDayRecordByDateUseCase: GetDayRecordByDateUseCase,
    private val getOrCreateDayRecordByDateUseCase: GetOrCreateDayRecordByDateUseCase,
    private val getOrCreateMomentRecordByDate: GetOrCreateMomentRecordByDate,
    private val saveDayRecordUseCase: SaveDayRecordUseCase,
    private val deleteDayRecordUseCase: DeleteDayRecordUseCase,
    private val saveMomentRecordUseCase: SaveMomentRecordUseCase,
    private val deleteMomentRecordUseCase: DeleteDayRecordUseCase,
) : ViewModel() {

    var state = MutableStateFlow<RecordState>(RecordState.Loading)
        private set

    fun loadPreviousState(previousState: RecordState) {
        state.update { previousState }
    }

    init {
        when (recordScreenType) {
            RecordScreenType.DAY -> initDayRecordScreen(recordTimestamp)
            RecordScreenType.MOMENT -> initMomentRecordScreen(recordTimestamp)
            else -> showMissingRecordTypeError()
        }
        initAvailableEmotions()
    }

    private fun showMissingRecordTypeError() {
        state.update { RecordState.Error.RecordTypeIsMissing }
    }

    private fun initAvailableEmotions() {
        viewModelScope.launch {
            getAllAvailableEmotionsUseCase()
                .catch { state.update { RecordState.Error.Data } }
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

    private fun initDayRecordScreen(recordTimestamp: Long? = null) {
        viewModelScope.launch {
            val date = recordTimestamp?.let { Date(it) }
            val dayRecord = getOrCreateDayRecordByDateUseCase(date)
                .catch { state.update { RecordState.Error.Data } }
                .stateIn(viewModelScope).value
            state.update { current ->
                when (current) {
                    is RecordState.Success.Day -> current.copy(record = dayRecord)
                    else -> RecordState.Success.Day(dayRecord)
                }
            }
        }
    }

    private fun initMomentRecordScreen(recordTimestamp: Long? = null) {
        viewModelScope.launch {
            val date = recordTimestamp?.let { Date(it) }
            val momentRecord = getOrCreateMomentRecordByDate(date)
                .catch { state.update { RecordState.Error.Data } }
                .stateIn(viewModelScope).value
            state.update { current ->
                when (current) {
                    is RecordState.Success.Moment -> current.copy(record = momentRecord)
                    else -> RecordState.Success.Moment(momentRecord)
                }
            }
        }
    }

    fun onAction(action: RecordAction) {
        when (action) {
            is RecordAction.Moment.MoodSliderChange -> updateMoodSlider(action.score)
            is RecordAction.NoteChange -> updateNote(action.note)
            is RecordAction.SaveRecord -> saveCurrentRecord()
            is RecordAction.BackClick -> navigateBack()
            is RecordAction.Day.AddMomentRecord -> navigateToMomentRecord()
            is RecordAction.Moment.EditMomentRecord -> navigateToMomentRecord(action.moment)
            else -> {}
        }
    }

    private fun navigateBack() {
        state.update { RecordState.Navigating(Back) }
    }

    private fun navigateToMomentRecord(momentRecord: Record.Moment? = null) {
        state.update { current ->
            RecordState.Navigating(
                navigationAction = ToMomentRecord(momentRecord),
                previousState = current,
            )
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