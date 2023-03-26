package com.lp.feature.dialog

import android.os.Build
import android.view.*
import com.lp.feature.core.BaseDialog
import com.lp.feature.databinding.DialogLoadingBinding

class LoadingDialog : BaseDialog<DialogLoadingBinding>() {

    private var isLoadingVisible = true

    override fun onCreateViewBinding(inflater: LayoutInflater): DialogLoadingBinding =
        DialogLoadingBinding.inflate(inflater)

    override fun onStart() {
        super.onStart()
        if (!isLoadingVisible) binding.dialogProgressBar.visibility = View.GONE
        dialog?.window?.run {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            dialog?.setCancelable(false)
            setBackgroundDrawableResource(android.R.color.transparent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val wic = decorView.windowInsetsController
                wic!!.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }

            attributes = attributes.run { this }
        }
    }
}