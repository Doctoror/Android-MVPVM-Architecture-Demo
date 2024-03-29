package com.doctoror.splittor.presentation.base

import androidx.annotation.MainThread

abstract class LifecyclePresenter {

    private var created = false

    @MainThread
    fun dispatchOnCreateIfNotCreated() {
        if (!created) {
            created = true
            onCreate()
        }
    }

    protected open fun onCreate() {}
}
