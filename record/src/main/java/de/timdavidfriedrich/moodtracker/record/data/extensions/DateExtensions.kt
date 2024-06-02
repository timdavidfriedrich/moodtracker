package de.timdavidfriedrich.moodtracker.record.data.extensions

import java.util.Calendar
import java.util.Date

internal fun Date.startOfTheDay(): Date {
    val calendar = Calendar.getInstance().apply {
        time = this@startOfTheDay
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.time
}

internal fun Date.endOfTheDay(): Date {
    val calendar = Calendar.getInstance().apply {
        time = this@endOfTheDay
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }
    return calendar.time
}

internal fun Date.startOfTheMinute(): Date {
    val calendar = Calendar.getInstance().apply {
        time = this@startOfTheMinute
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.time
}

internal fun Date.endOfTheMinute(): Date {
    val calendar = Calendar.getInstance().apply {
        time = this@endOfTheMinute
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }
    return calendar.time
}