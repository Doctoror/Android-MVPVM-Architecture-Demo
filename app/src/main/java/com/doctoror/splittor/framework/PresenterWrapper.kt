package com.doctoror.splittor.framework

import androidx.lifecycle.ViewModel

/**
 * Wraps Presenter into an [androidx.lifecycle.ViewModel] for DI
 */
abstract class PresenterWrapper<T> : ViewModel() {

    val unwrapped by lazy { makeWrapped() }

    abstract fun makeWrapped(): T
}
