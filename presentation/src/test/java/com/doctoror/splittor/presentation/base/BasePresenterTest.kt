package com.doctoror.splittor.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.junit.Assert.assertSame
import org.junit.Test

class BasePresenterTest {

    @Test(expected = IllegalArgumentException::class)
    fun throwsWhenTryingToAccessViewModelScopeWhenNotSet() {
        TestPresenter().getScope()
    }

    @Test
    fun providesViewModelScope() {
        val underTest = TestPresenter()
        val scope: CoroutineScope = MainScope()

        underTest.viewModelScopeProvider = { scope }

        assertSame(scope, underTest.getScope())
    }

    private class TestPresenter : BasePresenter<Any>(Any()) {

        override fun onCreate() {
        }

        fun getScope() = viewModelScope
    }
}
