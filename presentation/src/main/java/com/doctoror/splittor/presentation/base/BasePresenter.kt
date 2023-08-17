package com.doctoror.splittor.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope

abstract class BasePresenter<VM : Any>(val viewModel: VM) : LifecycleEventObserver {

    var viewModelScopeProvider: (() -> CoroutineScope)? = null

    protected val viewModelScope: CoroutineScope
        get() =
            requireNotNull(viewModelScopeProvider) { "viewModelScopeProvider not set" }
                .invoke()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            onCreate()
        }
    }

    abstract fun onCreate()
}
