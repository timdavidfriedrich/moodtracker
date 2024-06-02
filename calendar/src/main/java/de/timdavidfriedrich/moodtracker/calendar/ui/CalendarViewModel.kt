package de.timdavidfriedrich.moodtracker.calendar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.timdavidfriedrich.moodtracker.calendar.domain.usecases.GetAllDayRecordsUseCase
import de.timdavidfriedrich.moodtracker.common.domain.models.Record
import de.timdavidfriedrich.moodtracker.common.ui.extensions.toYearMonth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth

class CalendarViewModel(
    private val getAllDayRecordsUseCase: GetAllDayRecordsUseCase,
) : ViewModel() {

    var state = MutableStateFlow<CalendarState>(CalendarState.Loading)
        private set

    init {
        initState()
    }

    fun onAction(action: CalendarAction) {
        when (action) {
            is CalendarAction.JumpToToday -> jumpToToday()
            is CalendarAction.DismissMonthPicker -> dismissMonthPicker()
            is CalendarAction.OpenMonthPicker -> openMonthPicker()
            is CalendarAction.PickMonth -> pickMonth(action.month)
            is CalendarAction.SwitchCalendarType -> switchCalendarType()
            is CalendarAction.AddRecord -> navigateToRecord()
            is CalendarAction.EditRecord -> navigateToRecord(action.dayRecord)
            else -> Unit
        }
    }

    private fun initState() {
        updateMonthData()
    }

    private fun navigateToRecord(record: Record? = null) {
        state.value = CalendarState.Navigating(ToRecord(record))
    }

    private fun updateMonthData() {
        val previousState = state.value
        viewModelScope.launch {
            getAllDayRecordsUseCase()
                .catch { state.value = CalendarState.Error }
                .collect { dayRecords ->
                    when (previousState) {
                        is CalendarState.Success -> {
                            state.value = previousState.copy(
                                dayRecords = dayRecords.filter {
                                    it.date.toYearMonth() == previousState.month
                                }
                            )
                        }

                        else -> {
                            val monthToday = YearMonth.now()
                            state.value = CalendarState.Success(
                                month = monthToday,
                                dayRecords = dayRecords.filter {
                                    it.date.toYearMonth() == monthToday
                                },
                            )
                        }
                    }
                }
        }
    }

    private fun jumpToToday() {
        state.update {
            val currentMonth = YearMonth.now()
            if (it !is CalendarState.Success) return@update it
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
        state.update {
            if (it !is CalendarState.Success) return@update it
            it.copy(isMonthPickerVisible = true)
        }
    }

    private fun hideMonthPicker() {
        state.update {
            if (it !is CalendarState.Success) return@update it
            it.copy(isMonthPickerVisible = false)
        }
    }

    private fun dismissMonthPicker() {
        hideMonthPicker()
    }

    private fun pickMonth(month: YearMonth) {
        hideMonthPicker()
        state.update {
            if (it !is CalendarState.Success) return@update it
            it.copy(
                month = month,
                isCurrentMonthSelected = currentMonthEquals(month),
            )
        }
        updateMonthData()
    }

    private fun switchCalendarType() {
        state.update {
            if (it !is CalendarState.Success) return@update it
            it.copy(
                calendarType = when (it.calendarType) {
                    is CalendarType.Overview -> CalendarType.Detailed
                    is CalendarType.Detailed -> CalendarType.Overview
                }
            )
        }
    }

}