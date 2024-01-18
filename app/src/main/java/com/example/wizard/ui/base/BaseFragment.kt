package com.example.wizard.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wizard.ui.viewmodel.SharedViewModel

abstract class BaseFragment<Intent, State> :
    Fragment() {

    protected val viewModel: SharedViewModel by activityViewModels()

    abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    abstract fun invalidate(state: State)
}
