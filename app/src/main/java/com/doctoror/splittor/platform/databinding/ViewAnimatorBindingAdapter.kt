package com.doctoror.splittor.platform.databinding

import android.widget.ViewAnimator
import androidx.core.view.children
import androidx.databinding.BindingAdapter

object ViewAnimatorBindingAdapter {

    @JvmStatic
    @BindingAdapter("displayedChildId")
    fun displayedChildId(animator: ViewAnimator, displayedChildId: Int) {
        animator
            .displayedChild = animator
            .children
            .indexOfFirst { it.id == displayedChildId }
    }
}
