package com.doctoror.splittor.platform.view

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.widget.Checkable
import androidx.constraintlayout.widget.ConstraintLayout

class CheckableConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), Checkable {

    private val checkedStateSet = intArrayOf(android.R.attr.state_checked)

    private var externalClickListener: OnClickListener? = null

    private val internalClickListener = OnClickListener {
        toggle()
        externalClickListener?.onClick(it)
    }

    private var checked = false

    init {
        super.setOnClickListener(internalClickListener)
    }

    override fun setChecked(checked: Boolean) {
        this.checked = checked
    }

    override fun isChecked() = checked

    override fun toggle() {
        checked = !checked
    }

    override fun setOnClickListener(l: OnClickListener?) {
        externalClickListener = l
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray? {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            mergeDrawableStates(drawableState, checkedStateSet)
        }
        return drawableState
    }
}
