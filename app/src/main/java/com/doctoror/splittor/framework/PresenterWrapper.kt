package com.doctoror.splittor.framework

import androidx.lifecycle.ViewModel
import com.doctoror.splittor.presentation.base.BasePresenter

/**
 * Wraps Presenter into an [androidx.lifecycle.ViewModel] for DI
 */
abstract class PresenterWrapper<T : BasePresenter<*>> : ViewModel() {

    val unwrapped by lazy { makeWrapped() }

    abstract fun makeWrapped(): T
}
