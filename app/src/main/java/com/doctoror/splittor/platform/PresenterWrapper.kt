package com.doctoror.splittor.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.presentation.base.BasePresenter

open class PresenterWrapper<T : BasePresenter<*>>(val unwrapped: T) : ViewModel() {

    init {
        unwrapped.viewModelScopeProvider = { viewModelScope }
    }
}