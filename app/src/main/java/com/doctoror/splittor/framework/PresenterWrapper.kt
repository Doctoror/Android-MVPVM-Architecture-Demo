package com.doctoror.splittor.framework

import androidx.lifecycle.ViewModel
import com.doctoror.splittor.presentation.base.LifecyclePresenter

/**
 * Wraps Presenter into an [androidx.lifecycle.ViewModel] for DI
 */
abstract class PresenterWrapper<T : LifecyclePresenter> : ViewModel() {

    val unwrapped by lazy { makeWrapped() }

    abstract fun makeWrapped(): T
}
