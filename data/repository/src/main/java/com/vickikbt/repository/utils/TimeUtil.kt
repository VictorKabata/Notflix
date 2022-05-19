package com.vickikbt.repository.utils

import java.util.concurrent.TimeUnit

object TimeUtil {

    fun isTimeWithinInterval(
        timeInterval: Long,
        saveTime: Long,
        currentTime: Long = System.currentTimeMillis()
    ): Boolean {
        val saved = TimeUnit.MILLISECONDS.toMinutes(saveTime)
        val current = TimeUnit.MILLISECONDS.toMinutes(currentTime)
        val interval = TimeUnit.HOURS.toMinutes(timeInterval)

        val difference = current - saved

        return difference > interval
    }
}
