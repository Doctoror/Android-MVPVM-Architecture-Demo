package com.doctoror.splittor.framework

import androidx.lifecycle.Lifecycle
import com.doctoror.splittor.presentation.base.BasePresenter
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock

class PresenterWrapperTest {

    @Test
    fun dispatchesOnCreate() {
        val unwrapped = TestPresenter()
        val underTest = object : PresenterWrapper<TestPresenter>(unwrapped) {}

        underTest.onStateChanged(mock(), Lifecycle.Event.ON_CREATE)

        assertTrue(unwrapped.created)
    }

    private class TestPresenter : BasePresenter<Any>(Any()) {

        var created = false

        override fun onCreate() {
            created = true
        }
    }
}