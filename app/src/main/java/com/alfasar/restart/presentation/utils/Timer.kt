package com.alfasar.restart.presentation.utils

import android.os.CountDownTimer

private const val INTERVAL = 900L

interface TimerCallback {
    fun onTick(millis: Long)
    fun onFinish()
}

class Timer private constructor(
    millis: Long,
    private val callback: TimerCallback
) : CountDownTimer(millis, INTERVAL) {

    private var current: Long = millis

    override fun onTick(millisUntilFinished: Long) {
        current = millisUntilFinished
        callback.onTick(millisUntilFinished)
    }

    override fun onFinish() {
        callback.onFinish()
    }

    companion object {

        private var currentTimer: Timer? = null
        private var lastMillis: Long? = null

        private var callback: TimerCallback? = null

        val isStarted get() = currentTimer != null

        fun start(millis: Long, callback: TimerCallback) {
            currentTimer = Timer(millis, callback)
            this.callback = callback
            currentTimer?.start()
        }

        fun stop() {
            lastMillis = currentTimer?.current
            currentTimer?.cancel()
        }

        fun resume() {
            val millis = lastMillis ?: return
            val callback = callback ?: return
            start(millis, callback)
        }

        fun cancel() {
            currentTimer?.cancel()
            currentTimer = null
            lastMillis = null
            callback = null
        }
    }

}
