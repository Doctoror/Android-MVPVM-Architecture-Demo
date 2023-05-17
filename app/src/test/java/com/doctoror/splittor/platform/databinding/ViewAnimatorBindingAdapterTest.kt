package com.doctoror.splittor.platform.databinding

import android.view.View
import android.widget.ViewAnimator
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ViewAnimatorBindingAdapterTest {

    private val underTest = ViewAnimatorBindingAdapter

    @Test
    fun displayedChildIdIsJvmStatic() {
        underTest::displayedChildId.annotations.first {
            it.annotationClass == JvmStatic::class
        }
    }

    @Test
    fun displayedChildTriesToSetMinusOneWhenChildByIdNotFound() {
        val viewAnimator: ViewAnimator = mock()

        val child1: View = mock { whenever(it.id).thenReturn(101) }
        val child2: View = mock { whenever(it.id).thenReturn(102) }

        whenever(viewAnimator.childCount).thenReturn(2)
        whenever(viewAnimator.getChildAt(0)).thenReturn(child1)
        whenever(viewAnimator.getChildAt(1)).thenReturn(child2)

        underTest.displayedChildId(viewAnimator, 1214)

        verify(viewAnimator).displayedChild = -1
    }

    @Test
    fun setsDisplayedChildById() {
        val viewAnimator: ViewAnimator = mock()

        val child1: View = mock { whenever(it.id).thenReturn(10) }
        val child2: View = mock { whenever(it.id).thenReturn(11) }

        whenever(viewAnimator.childCount).thenReturn(2)
        whenever(viewAnimator.getChildAt(0)).thenReturn(child1)
        whenever(viewAnimator.getChildAt(1)).thenReturn(child2)

        underTest.displayedChildId(viewAnimator, 11)

        verify(viewAnimator).displayedChild = 1
    }
}
