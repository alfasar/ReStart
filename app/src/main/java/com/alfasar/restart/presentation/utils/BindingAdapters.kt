package com.alfasar.restart.presentation.utils

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.alfasar.restart.domain.FabState
import com.alfasar.restart.presentation.timer.TimerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter(value = ["text"], requireAll = false)
fun setTimerViewText(view: TimerView, text: Long) {
    view.updateTime(text)
}

@BindingAdapter(value = ["onRestartClick"], requireAll = false)
fun setTimerViewClickListener(view: TimerView, action: () -> Unit) {
    view.setOnRestartClickListener { action() }
}

@BindingAdapter(value = ["state"], requireAll = false)
fun setTimerFragmentFabState(view: FloatingActionButton, state: FabState) {
    val drawable = ContextCompat.getDrawable(view.context, state.res)
    view.setImageDrawable(drawable)
}