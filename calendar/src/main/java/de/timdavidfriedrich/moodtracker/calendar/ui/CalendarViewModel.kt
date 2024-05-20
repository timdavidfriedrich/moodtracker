package de.timdavidfriedrich.moodtracker.calendar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.timdavidfriedrich.moodtracker.calendar.domain.usecases.GetAllDayRecordsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth

class CalendarViewModel(
    private val getAllDayRecordsUseCase: GetAllDayRecordsUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<CalendarUiState>(CalendarUiState.Loading)
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    init {
        initUiState()
    }

    fun onAction(action: CalendarAction) {
        when (action) {
            is CalendarAction.JumpToToday -> jumpToToday()
            is CalendarAction.DismissMonthPicker -> dismissMonthPicker()
            is CalendarAction.OpenMonthPicker -> openMonthPicker()
            is CalendarAction.PickMonth -> pickMonth(action.month)
            is CalendarAction.SwitchCalendarType -> switchCalendarType()
            else -> Unit
        }
    }

    private fun initUiState() {
        _uiState.value = CalendarUiState.Success()
        updateMonthData()
    }

    private fun updateMonthData() {
        viewModelScope.launch {
            getAllDayRecordsUseCase()
                .onStart { _uiState.value = CalendarUiState.Loading }
                .catch { _uiState.value = CalendarUiState.Error }
                .collect { dayRecords ->
                    if (_uiState.value !is CalendarUiState.Success) {
                        _uiState.value = CalendarUiState.Success()
                    }
                    _uiState.update {
                        (it as CalendarUiState.Success).copy(dayRecords = dayRecords)
                    }
                }
        }
    }

    private fun jumpToToday() {
        _uiState.update {
            val currentMonth = YearMonth.now()
            if (it !is CalendarUiState.Success) return@update it
            it.copy(
                month = currentMonth,
                isCurrentMonthSelected = currentMonthEquals(currentMonth),
            )
        }
        updateMonthData()
    }

    private fun currentMonthEquals(month: YearMonth): Boolean {
        return month == YearMonth.now()
    }

    private fun openMonthPicker() {
        _uiState.update {
            if (it !is CalendarUiState.Success) return@update it
            it.copy(isMonthPickerVisible = true)
        }
    }

    private fun hideMonthPicker() {
        _uiState.update {
            if (it !is CalendarUiState.Success) return@update it
            it.copy(isMonthPickerVisible = false)
        }
    }

    private fun dismissMonthPicker() {
        hideMonthPicker()
    }

    private fun pickMonth(month: YearMonth) {
        hideMonthPicker()
        _uiState.update {
            if (it !is CalendarUiState.Success) return@update it
            it.copy(
                month = month,
                isCurrentMonthSelected = currentMonthEquals(month),
            )
        }
        updateMonthData()
    }

    private fun switchCalendarType() {
        _uiState.update {
            if (it !is CalendarUiState.Success) return@update it
            it.copy(
                calendarType = when (it.calendarType) {
                    is CalendarType.Overview -> CalendarType.Detailed
                    is CalendarType.Detailed -> CalendarType.Overview
                }
            )
        }
    }

}