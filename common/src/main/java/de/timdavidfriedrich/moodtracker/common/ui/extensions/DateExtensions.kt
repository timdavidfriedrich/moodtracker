package de.timdavidfriedrich.moodtracker.common.ui.extensions

import android.text.format.DateUtils
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
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

fun Date.toYearMonth(): YearMonth {
    val localDate = toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    return YearMonth.of(localDate.year, localDate.month)
}

fun Date.toFormattedDayString(): String {
    return if (DateUtils.isToday(time)) "Today"
    else toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}

fun Date.toFormattedDayStringWithTime(): String {
    if (DateUtils.isToday(time)) {
        return buildString {
            // TODO: Replace with variable
            append("Today")
            append(", ")
            append(
                toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime()
                    .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
            )
        }
    }
    return toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
        .format(
            DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.SHORT,
            )
        )
}