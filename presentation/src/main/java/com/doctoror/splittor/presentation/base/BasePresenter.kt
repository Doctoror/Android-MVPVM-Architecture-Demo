package com.doctoror.splittor.presentation.base

import kotlinx.coroutines.CoroutineScope

abstract class BasePresenter<VM : Any>(val viewModel: VM) {

    var viewModelScopeProvider: (() -> CoroutineScope)? = null

    protected val viewModelScope: CoroutineScope
        get() =
            requireNotNull(viewModelScopeProvider) { "viewModelScopeProvider not set" }
                .invoke()

    abstract fun onCreate()
}
