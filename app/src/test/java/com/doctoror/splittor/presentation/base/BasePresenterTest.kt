package com.doctoror.splittor.presentation.base

import io.reactivex.rxjava3.core.Completable
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicBoolean

class BasePresenterTest {

    @Test
    fun disposesDisposablesOnDestroy() {
        val disposable1Disposed = AtomicBoolean()
        val disposable2Disposed = AtomicBoolean()

        val underTest = object : BasePresenter() {

            override fun onCreate() {
                Completable
                    .never()
                    .doOnDispose { disposable1Disposed.set(true) }
                    .subscribe()
                    .disposeOnDestroy()

                Completable
                    .never()
                    .doOnDispose { disposable2Disposed.set(true) }
                    .subscribe()
                    .disposeOnDestroy()
            }
        }

        underTest.onCreate()
        underTest.onDestroy()

        assertTrue(disposable1Disposed.get())
        assertTrue(disposable2Disposed.get())
    }
}
