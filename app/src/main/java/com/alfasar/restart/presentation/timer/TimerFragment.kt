package com.alfasar.restart.presentation.timer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.alfasar.restart.R
import com.alfasar.restart.databinding.FragmentTimerBinding
import com.alfasar.restart.presentation.utils.BindingFragment

class TimerFragment : BindingFragment<FragmentTimerBinding, TimerViewModel>(R.layout.fragment_timer) {

    override val viewModel: TimerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
