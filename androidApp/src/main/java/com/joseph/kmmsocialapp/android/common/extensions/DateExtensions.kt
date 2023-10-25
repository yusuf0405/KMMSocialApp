package com.joseph.kmmsocialapp.android.common.extensions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun emptyString(): String = ""

/** Create new [Date] with addition [days] to current [Date] */
fun Date.addDays(days: Int, minutes: Int = 0) =
    Date(this.year, this.month, this.date + days, this.hours, this.minutes + minutes, this.seconds)

/** Create new [Date] equals start of day of the current [Date] */
fun Date.startOfDay() = Date(this.year, this.month, this.date)

/** Возвращает String в формате dd.MM.yyyy пример: 31.03.2021 */
fun Date.formatAsDayMonthYear(): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("GMT")
    return format.format(this)
}

/** Возвращает String в формате dd.MM.yyyy пример: 19:00 */
fun Date.formatAsHoursMinutes(): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("GMT")
    return format.format(this)
}

/** Возвращает String в формате dd MMMM yyyy пример: 31 января 2021 */
fun Date.formatAsFullyDayMonthYear(): String {
    val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return format.format(this)
}

/** Возвращает String в ISO формате yyyy-MM-dd'T'HH:mm:ssZZZZZ пример: "2021-09-15T07:09:32+00:00" */
fun Date.formatAsISO(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
    return format.format(this)
}

/** Возвращает Date из String в ISO формате yyyy-MM-dd'T'HH:mm:ssZZZZZ пример: "2021-09-15T07:09:32+00:00" */
fun String.formatISOAsDateTime(): Date? {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
        format.parse(this)
    } catch (e: Exception) {
        null
    }
}

/** Возвращает Date из String в ISO формате yyyy-MM-dd пример: "2021-09-15" */
fun String.formatISOAsDate(): Date? {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.formatISOAsOffsetDateTime(): OffsetDateTime? = try {
    if (this.isEmpty()) null
    else {
        val pattern = "yyyy-MM-dd'T'HH:mm:ssXXX"
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        OffsetDateTime.parse(this, formatter)
    }
} catch (e: Exception) {
    null
}

/** Возвращает String в формате dd MMM yyyy пример: "15 дек. 2021" */
fun Date.formatAsReadableString(): String {
    val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return format.format(this) ?: emptyString()
}

/** Возвращает String в формате yyyy пример: "2021" */
fun Date.getYearString(): String {
    val format = SimpleDateFormat("yyyy", Locale.getDefault())
    return format.format(this) ?: emptyString()
}

fun minimalLocalDate(): LocalDate = LocalDate.of(0, 0, 0)

