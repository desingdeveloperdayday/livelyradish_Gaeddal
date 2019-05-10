package com.hyeyeon2371.gaeddal

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun timerForSDK26() {
        val dateTimeStr = "2019-05-10 17:33:00"
        val fromDateTime = LocalDateTime.now()
        val toDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val duration = Duration.between(fromDateTime, toDateTime).toMillis()
        val minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
        // val seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(minutes)
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        System.out.println("$hours H, $remainingMinutes M")
    }

    @Test
    fun timerForSDK19() {
        val dateTimeStr = "2019-05-10 17:33:00"
        val fromDateTime = Calendar.getInstance()
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val toDateTime = parser.parse(dateTimeStr) // Wed May 20 17:30:00 KST 2020

        val diff = toDateTime.time - fromDateTime.time.time
        val seconds = diff / 1000
        val minutes = seconds / 60

        val hours = minutes / 60
        val days = hours / 24
        val remainingMinutes = minutes % 60

        System.out.println("current $fromDateTime")
        System.out.println("reserved $toDateTime")
        System.out.println("$days D, $hours H, $remainingMinutes M, $seconds S")
    }
}
