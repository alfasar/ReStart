package com.alfasar.restart.presentation.timer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alfasar.restart.R
import com.alfasar.restart.presentation.utils.toHumanTime

private const val TEXT_VIEW_INDEX = 0
private const val IMAGE_BUTTON_INDEX = 1

class TimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var timeTv: TextView
    private lateinit var restartIb: ImageButton

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.view_timer, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        timeTv = getChildAt(TEXT_VIEW_INDEX) as TextView
        restartIb = getChildAt(IMAGE_BUTTON_INDEX) as ImageButton
    }

    fun updateTime(time: Long) {
        timeTv.text = time.toHumanTime()
        invalidate()
    }

    fun setOnRestartClickListener(action: () -> Unit) {
        restartIb.setOnClickListener { action() }
        invalidate()
    }

}
