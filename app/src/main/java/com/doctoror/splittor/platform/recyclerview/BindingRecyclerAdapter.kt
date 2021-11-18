package com.doctoror.splittor.platform.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerAdapter<Binding : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    private val layoutInflater: LayoutInflater,
    private val modelId: Int
) : RecyclerView.Adapter<BindingRecyclerAdapter.BindingViewHolder<Binding>>() {

    private val items = mutableListOf<Any>()

    fun replaceItems(newItems: List<Any>?) {
        items.clear()
        if (newItems != null) {
            items.addAll(newItems)
        }
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BindingViewHolder(
        DataBindingUtil.inflate(layoutInflater, layoutId, parent, false) as Binding
    )

    override fun onBindViewHolder(holder: BindingViewHolder<Binding>, position: Int) {
        holder.binding.setVariable(modelId, items[position])
    }

    class BindingViewHolder<Binding : ViewDataBinding>(val binding: Binding) :
        RecyclerView.ViewHolder(binding.root)
}
