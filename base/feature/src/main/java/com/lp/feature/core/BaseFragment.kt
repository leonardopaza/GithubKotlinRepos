package com.lp.feature.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.lp.feature.dialog.LoadingDialog

abstract class BaseFragment : Fragment() {
    private var loadingDialogFragment: LoadingDialog? = null
        get() {
            if (field == null)
                field = LoadingDialog()

            return field
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addObservers(viewLifecycleOwner)
        configureView()
    }

    open fun addObservers(owner: LifecycleOwner) = Unit

    open fun configureView() = Unit

    fun showLoading() {
        childFragmentManager.let {
            it.executePendingTransactions()
            if (loadingDialogFragment?.isAdded?.not() == true) {
                loadingDialogFragment?.show(this)
            }
        }
    }

    fun hideLoading() {
        loadingDialogFragment?.dismissAllowingStateLoss()
        loadingDialogFragment = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loadingDialogFragment = null
    }
}