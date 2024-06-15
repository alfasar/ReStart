package com.alfasar.restart.presentation.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfasar.restart.domain.FabState
import com.alfasar.restart.domain.TimerState
import com.alfasar.restart.presentation.utils.FLOW_SUBSCRIPTION_TIMEOUT
import com.alfasar.restart.presentation.utils.Timer
import com.alfasar.restart.presentation.utils.TimerCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private const val TIMER_DEFAULT_VALUE = 10_000L

class TimerViewModel : ViewModel(), TimerCallback {

    val timerState: StateFlow<TimerState>
        field = MutableStateFlow(TimerState.IDLE)

    val timerValue: StateFlow<Long>
        field = MutableStateFlow(TIMER_DEFAULT_VALUE)

    val fabState: StateFlow<FabState> = timerState.map { state ->
        when (state) {
            TimerState.IDLE, TimerState.PAUSED, TimerState.WAITING -> FabState.PLAY
            TimerState.RUNNING, TimerState.ALARMING -> FabState.STOP
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(FLOW_SUBSCRIPTION_TIMEOUT), FabState.PLAY)

    fun onFabClick() {
        when (fabState.value) {
            FabState.PLAY -> {
                if (!Timer.isStarted) {
                    Timer.start(timerValue.value, this)
                } else {
                    Timer.resume()
                }
                timerState.value = TimerState.RUNNING
            }

            FabState.STOP -> {
                Timer.stop()
                timerState.value = TimerState.PAUSED
            }
        }
    }

    fun restart() {
        Timer.cancel()
        Timer.start(TIMER_DEFAULT_VALUE, this)
        timerState.value = TimerState.RUNNING
    }

    override fun onTick(millis: Long) {
        timerValue.value = millis
    }

    override fun onFinish() {
        Timer.cancel()
        timerState.value = TimerState.IDLE
        timerValue.value = TIMER_DEFAULT_VALUE
    }

}
