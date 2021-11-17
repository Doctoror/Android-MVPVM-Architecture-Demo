package com.doctoror.splittor.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    abstract fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposables.clear()
    }

    fun Disposable.disposeOnDestroy() {
        disposables.add(this)
    }
}
