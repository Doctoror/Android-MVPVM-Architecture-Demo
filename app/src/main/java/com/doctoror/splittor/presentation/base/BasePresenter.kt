package com.doctoror.splittor.presentation.base

import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter : ViewModel(), LifecycleEventObserver {

    private val disposables = CompositeDisposable()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate()
            Lifecycle.Event.ON_DESTROY -> onDestroy()
        }
    }

    abstract fun onCreate()

    fun onDestroy() {
        disposables.clear()
    }

    fun Disposable.disposeOnDestroy() {
        disposables.add(this)
    }
}
