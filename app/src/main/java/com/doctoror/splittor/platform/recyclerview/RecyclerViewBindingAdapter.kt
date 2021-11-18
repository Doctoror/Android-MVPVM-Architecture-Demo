package com.doctoror.splittor.platform.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapter {

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: Collection<Any>?) {
        val adapter = recyclerView.adapter
            ?: throw IllegalStateException("Cannot bind until RecyclerView.Adapter is set")

        if (adapter !is BindingRecyclerAdapter<*>) {
            throw IllegalStateException("Can bind only to BindingRecyclerAdapter")
        }

        adapter.replaceItems(items?.toList())
        adapter.notifyDataSetChanged()
    }
}
