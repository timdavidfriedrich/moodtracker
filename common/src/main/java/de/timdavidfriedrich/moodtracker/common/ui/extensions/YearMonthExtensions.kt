package de.timdavidfriedrich.moodtracker.common.ui.extensions

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun YearMonth.toFormattedString(): String {
    return format(
        DateTimeFormatter.ofPattern(
            "MMMM u",
            Locale.getDefault(),
        )
    )
}

fun YearMonth.toMilliseconds(): Long {
    return Date.from(
        LocalDate.of(year, month, 1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
    ).time
}

fun Long.toYearMonth(): YearMonth {
    val localDate = Date(this).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    return YearMonth.of(localDate.year, localDate.month)
}
