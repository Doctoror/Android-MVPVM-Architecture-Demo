package com.doctoror.splittor.platform.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.doctoror.splittor.platform.recyclerview.BindingRecyclerAdapter

object RecyclerViewBindingAdapter {

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: Collection<Any>?) {
        val adapter = recyclerView.adapter
            ?: throw IllegalStateException("Cannot bind until RecyclerView.Adapter is set")

        if (adapter !is BindingRecyclerAdapter<*, *>) {
            throw IllegalStateException("Can bind only to BindingRecyclerAdapter")
        }

        @Suppress("UNCHECKED_CAST")
        (adapter as BindingRecyclerAdapter<*, Any>).replaceItems(items?.toList())
        adapter.notifyDataSetChanged()
    }
}
