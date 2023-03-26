package com.lp.feature.core

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<Binding : ViewBinding> : DialogFragment() {

    private var mView: View? = null
    private var _binding: Binding? = null
        get() {
            if (field == null)
                field = onCreateViewBinding(layoutInflater)
            return field
        }

    protected val binding: Binding
        get() = _binding!!

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            mView = binding.root
        } else {
            dismiss()
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers(viewLifecycleOwner)
        setupView()
    }

    abstract fun onCreateViewBinding(inflater: LayoutInflater): Binding

    open fun setupView() = Unit

    open fun addObservers(owner: LifecycleOwner) = Unit

    fun show(fragmentContainer: BaseFragment){
        show(
            fragmentContainer.childFragmentManager,
            tag
        )
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(true)
        dialog?.window?.apply {
            setGravity(Gravity.CENTER)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded || isLoading) {
            isLoading = true
            try {
                super.show(manager, tag)
            } catch (e: Exception) {
                return
            }
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isLoading) {
            isLoading = false
            super.dismissAllowingStateLoss()
        }
    }

    override fun dismiss() {
        lifecycleScope.launchWhenResumed {
            super.dismiss()
            mView = null
        }
    }
}