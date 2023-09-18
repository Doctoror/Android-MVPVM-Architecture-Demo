package com.doctoror.splittor.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.junit.Assert.assertEquals
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

    @Test
    fun dispatchesOnCreateOnce() {
        val underTest = TestPresenter()

        underTest.dispatchOnCreateIfNotCreated()
        underTest.dispatchOnCreateIfNotCreated()

        assertEquals(1, underTest.onCreateCount)
    }

    private class TestPresenter : BasePresenter<Any>(Any()) {

        var onCreateCount = 0

        override fun onCreate() {
            onCreateCount++
        }

        fun getScope() = viewModelScope
    }
}
