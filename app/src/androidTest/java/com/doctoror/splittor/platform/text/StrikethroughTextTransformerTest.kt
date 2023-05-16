package com.doctoror.splittor.platform.text

import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import org.junit.Assert.assertEquals
import org.junit.Test

class StrikethroughTextTransformerTest {

    private val strikethroughSpanFactory: () -> Any = { EqualsStrikethroughSpan() }

    private val underTest = StrikethroughTextTransformer(strikethroughSpanFactory)

    @Test
    fun strikesthroughText() {
        val input = "input"

        assertEquals(
            SpannableString(input).apply {
                setSpan(
                    strikethroughSpanFactory(),
                    0,
                    input.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
            },
            underTest.strikethrough(input)
        )
    }

    private class EqualsStrikethroughSpan : StrikethroughSpan() {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
}
