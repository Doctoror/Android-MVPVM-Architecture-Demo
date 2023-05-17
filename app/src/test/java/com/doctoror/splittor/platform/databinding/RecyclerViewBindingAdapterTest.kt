package com.doctoror.splittor.platform.databinding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.doctoror.splittor.platform.recyclerview.BindingRecyclerAdapter
import org.junit.Test
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RecyclerViewBindingAdapterTest {

    private val underTest = RecyclerViewBindingAdapter

    @Test
    fun setItemsIsJvmStatic() {
        underTest::setItems.annotations.first {
            it.annotationClass == JvmStatic::class
        }
    }

    @Test(expected = IllegalStateException::class)
    fun setItemsThrowsIllegalStateExceptionWhenNoAdapter() {
        val recyclerView: RecyclerView = mock()

        underTest.setItems(recyclerView, emptyList())
    }

    @Test(expected = IllegalStateException::class)
    fun setItemsThrowsIllegalStateExceptionWhenAdapterIsNotBindingRecyclerAdapter() {
        val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> = mock()
        val recyclerView: RecyclerView = mock { whenever(it.adapter).thenReturn(adapter) }

        underTest.setItems(recyclerView, emptyList())
    }

    @Test
    fun setItemsReplacesItemsAndCallsNotifyDataSetChanged() {
        val adapter: BindingRecyclerAdapter<ViewDataBinding, Any> = mock()
        val recyclerView: RecyclerView = mock { whenever(it.adapter).thenReturn(adapter) }
        val newItems = listOf(1L, 2L, 5L)

        underTest.setItems(recyclerView, newItems)

        inOrder(adapter) {
            verify(adapter).replaceItems(newItems)
            verify(adapter).notifyDataSetChanged()
        }
    }
}
