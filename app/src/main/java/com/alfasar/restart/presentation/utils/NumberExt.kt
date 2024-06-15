package com.alfasar.restart.presentation.utils

private const val HOUR_FORMAT = "%02d:%02d:%02d"
private const val MINUTE_FORMAT = "%02d:%02d"

fun Long.toHumanTime(): String {
    val second: Long = (this / 1000) % 60
    val minute: Long = (this / (1000 * 60)) % 60
    val hour: Long = (this / (1000 * 60 * 60)) % 24

    return if (hour == 0L) {
        String.format(MINUTE_FORMAT, minute, second)
    }else {
        String.format(HOUR_FORMAT, hour, minute, second)
    }
}
