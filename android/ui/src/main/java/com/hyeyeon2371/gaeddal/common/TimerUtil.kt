package com.hyeyeon2371.gaeddal.common

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Build
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
class TimerUtil(private val dateTimeStr: String) {
    private val TIMER_PERIOD: Long = 60000
    private val TIMER_DELAY: Long = 0

    private var minutes: MutableLiveData<String> = MutableLiveData()
    private var hours: MutableLiveData<String> = MutableLiveData()

    fun addObservers(lifecycleOwner: LifecycleOwner, hourObserver: Observer<String>, minuteObserver: Observer<String>) {
        minutes.observe(lifecycleOwner, minuteObserver)
        hours.observe(lifecycleOwner, hourObserver)
    }

    fun startTimer() {
        calculateTime(dateTimeStr).let {
            hours.postValue(it.split(":")[0])
            minutes.postValue(it.split(":")[1])

            object : TimerTask() {
                override fun run() {
                    val minuteInt = minutes.value?.toInt() ?: 0
                    val hourInt = hours.value?.toInt() ?: 0

                    if (minuteInt > 0) {
                        minutes.postValue(
                            if (minuteInt < 10) {
                                "0" + (minuteInt - 1).toString()
                            } else {
                                (minuteInt - 1).toString()
                            }
                        )
                    } else {
                        if (hourInt > 0) {
                            hours.postValue("0" + (hourInt - 1))
                            minutes.postValue("59")
                        } else {
                            //end
                            hours.postValue("00")
                            minutes.postValue("00")
                            this.cancel()
                        }
                    }
                }
            }.let { timerTask ->
                Timer().schedule(timerTask, TIMER_DELAY, TIMER_PERIOD)
            }
        }
    }

    private fun calculateTime(dateTimeStr: String): String {
        val durationMinutes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).let { toDateTime ->
                Duration.between(LocalDateTime.now(), toDateTime).toMillis().let { duration ->
                    TimeUnit.MILLISECONDS.toMinutes(duration)
                }
            }
        } else {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").let { parser ->
                parser.parse(dateTimeStr).let { toDateTime ->
                    val duration = toDateTime.time - Calendar.getInstance().time.time
                    val seconds = duration / 1000
                    seconds / 60
                }
            }
        }

        hours.value = (durationMinutes / 60).toString()
        minutes.value = (durationMinutes % 60).toString()

        hours.postValue(hours.value)
        minutes.postValue(minutes.value)

        return "${hours.value}:${minutes.value}"
    }

}