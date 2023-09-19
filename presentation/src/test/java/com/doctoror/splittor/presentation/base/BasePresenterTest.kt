package com.doctoror.splittor.presentation.base

import org.junit.Assert.assertEquals
import org.junit.Test

class BasePresenterTest {

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
    }
}
