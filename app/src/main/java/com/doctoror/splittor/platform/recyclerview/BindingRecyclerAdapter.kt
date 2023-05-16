package com.doctoror.splittor.platform.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerAdapter<Binding : ViewDataBinding, Model : Any>(
    @LayoutRes private val layoutId: Int,
    private val layoutInflater: LayoutInflater,
    private val modelId: Int,
    private val onItemClickListener: OnItemClickListener<Model>? = null
) : RecyclerView.Adapter<BindingRecyclerAdapter.BindingViewHolder<Binding>>() {

    private val items = mutableListOf<Model>()

    fun replaceItems(newItems: List<Model>?) {
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
        if (onItemClickListener != null) {
            holder.binding.root.setOnClickListener {
                onItemClickListener.invoke(items[position])
            }
        }
    }

    class BindingViewHolder<Binding : ViewDataBinding>(val binding: Binding) :
        RecyclerView.ViewHolder(binding.root)

    fun interface OnItemClickListener<Model : Any> : (Model) -> Unit
}
