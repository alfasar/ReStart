package com.alfasar.restart.domain

import androidx.annotation.DrawableRes
import com.alfasar.restart.R

enum class FabState(@DrawableRes val res: Int) {
    PLAY(R.drawable.ic_play),
    STOP(R.drawable.ic_stop)
}
