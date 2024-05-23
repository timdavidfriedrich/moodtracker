package de.timdavidfriedrich.moodtracker.calendar.ui.extensions

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

internal fun YearMonth.toFormattedString(): String {
    return format(
        DateTimeFormatter.ofPattern(
            "MMMM u",
            Locale.getDefault(),
        )
    )
}

internal fun YearMonth.toMilliseconds(): Long {
    return Date.from(
        LocalDate.of(year, month, 1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
    ).time
}

internal fun Long.toYearMonth(): YearMonth {
    val localDate = Date(this).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    return YearMonth.of(localDate.year, localDate.month)
}

internal fun Date.toYearMonth(): YearMonth {
    val localDate = toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    return YearMonth.of(localDate.year, localDate.month)
}