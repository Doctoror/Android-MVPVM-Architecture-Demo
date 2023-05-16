package com.doctoror.splittor.platform.text

import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan

class StrikethroughTextTransformer(
    private val strikethroughSpanFactory: () -> Any = { StrikethroughSpan() }
) {

    fun strikethrough(input: CharSequence): CharSequence = SpannableString(input).apply {
        setSpan(
            strikethroughSpanFactory(),
            0,
            input.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
    }
}
