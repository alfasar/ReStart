package com.alfasar.restart.domain

enum class TimerState {
    IDLE, // Состояние бездействия (по умолчанию).
    RUNNING, // Состояние работы.
    PAUSED, // Пауза.
    ALARMING, // Таймер отработал и ожидает сброса звонка.
    WAITING // Ожидание перед новым циклом.
}
