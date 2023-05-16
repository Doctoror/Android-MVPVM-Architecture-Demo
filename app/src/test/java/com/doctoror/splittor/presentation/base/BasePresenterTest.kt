package com.doctoror.splittor.presentation.base

import androidx.lifecycle.Lifecycle
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock

class BasePresenterTest {

    @Test
    fun dispatchesOnCreate() {
        var created = false
        val underTest = object : BasePresenter() {

            override fun onCreate() {
                created = true
            }
        }

        underTest.onStateChanged(mock(), Lifecycle.Event.ON_CREATE)

        assertTrue(created)
    }
}
