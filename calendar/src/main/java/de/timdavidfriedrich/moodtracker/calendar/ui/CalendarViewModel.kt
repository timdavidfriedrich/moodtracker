package de.timdavidfriedrich.moodtracker.calendar.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import de.timdavidfriedrich.moodtracker.calendar.domain.usecases.GetAllDayRecordsUseCase
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.domain.usecases.GetAllAvailableEmotionsUseCase
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toYearMonth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.Locale

class CalendarViewModel(
    private val getAllPossibleEmotionsUseCase: GetAllAvailableEmotionsUseCase,
    private val getAllDayRecordsUseCase: GetAllDayRecordsUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<CalendarUiState>(CalendarUiState.Loading)
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    init {
        initUiState()
    }

    fun onAction(action: CalendarAction) {
        when (action) {
            is CalendarAction.AddRecord -> {}
            is CalendarAction.EditRecord -> {}
            is CalendarAction.JumpToToday -> jumpToToday()
            is CalendarAction.DismissMonthPicker -> dismissMonthPicker()
            is CalendarAction.OpenMonthPicker -> openMonthPicker()
            is CalendarAction.PickMonth -> pickMonth(action.month)
            is CalendarAction.SwitchCalendarType -> switchCalendarType()
        }
    }

    private fun initUiState() {
        _uiState.value = CalendarUiState.Success()
        updateMonthData()
    }

    private fun updateMonthData() {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val sampleDayRecords = listOf(
            Record.Day(date = format.parse("2024-02-01")!!),
            Record.Day(date = format.parse("2023-08-22")!!),
            Record.Day(date = format.parse("2024-01-01")!!),
            Record.Day(date = format.parse("2024-05-11")!!),
            Record.Day(date = format.parse("2024-05-12")!!),
        )

        _uiState.update { current ->
            if (current !is CalendarUiState.Success) return@update current
            current.copy(
                dayRecords = sampleDayRecords.filter {
                    it.date.time.toYearMonth() == current.month
                }
            )
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
        Log.d(
            "isCurrentMonthSelected",
            "$month (selected) == ${YearMonth.now()} (now) = ${month == YearMonth.now()}"
        )
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