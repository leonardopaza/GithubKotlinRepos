package com.lp.feature.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentBinding<Binding : ViewBinding> : BaseFragment() {
    private var _binding: Binding? = null
        get() {
            if (field == null)
                field = onCreateViewBinding(layoutInflater)
            return field
        }

    protected val binding: Binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    abstract fun onCreateViewBinding(inflater: LayoutInflater): Binding

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}