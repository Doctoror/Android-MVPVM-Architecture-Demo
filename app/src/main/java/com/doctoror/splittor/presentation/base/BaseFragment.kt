package com.doctoror.splittor.presentation.base

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private val disposables = CompositeDisposable()

    protected fun Disposable.disposeOnDestroy() {
        disposables.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
