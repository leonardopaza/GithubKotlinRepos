package com.lp.feature.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addObservers(viewLifecycleOwner)
        configureView()
    }

    open fun addObservers(owner: LifecycleOwner) = Unit

    open fun configureView() = Unit
}