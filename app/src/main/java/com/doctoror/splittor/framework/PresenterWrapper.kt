package com.doctoror.splittor.framework

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.presentation.base.BasePresenter

/**
 * Wraps Presenter into an [androidx.lifecycle.ViewModel] for DI
 */
open class PresenterWrapper<T : BasePresenter<*>>(val unwrapped: T) : ViewModel(),
    LifecycleEventObserver {

    init {
        unwrapped.viewModelScopeProvider = { viewModelScope }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            unwrapped.onCreate()
        }
    }
}