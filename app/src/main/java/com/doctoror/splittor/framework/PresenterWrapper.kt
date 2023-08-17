package com.doctoror.splittor.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.presentation.base.BasePresenter

/**
 * Wraps Presenter into an [androidx.lifecycle.ViewModel] for DI
 */
open class PresenterWrapper<T : BasePresenter<*>>(val unwrapped: T) : ViewModel() {

    init {
        unwrapped.viewModelScopeProvider = { viewModelScope }
    }
}