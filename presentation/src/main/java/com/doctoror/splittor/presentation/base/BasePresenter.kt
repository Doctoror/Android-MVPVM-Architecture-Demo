package com.doctoror.splittor.presentation.base

import androidx.annotation.MainThread
import kotlinx.coroutines.CoroutineScope

abstract class BasePresenter<VM : Any>(val viewModel: VM) {

    private var created = false

    var viewModelScopeProvider: (() -> CoroutineScope)? = null

    protected val viewModelScope: CoroutineScope
        get() = requireNotNull(viewModelScopeProvider) { "viewModelScopeProvider not set" }.invoke()

    @MainThread
    fun dispatchOnCreateIfNotCreated() {
        if (!created) {
            created = true
            onCreate()
        }
    }

    protected open fun onCreate() {}
}
