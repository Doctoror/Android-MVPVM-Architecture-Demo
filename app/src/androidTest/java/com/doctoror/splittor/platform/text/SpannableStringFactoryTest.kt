package com.doctoror.splittor.platform.text

import android.text.SpannableString
import org.junit.Assert.assertEquals
import org.junit.Test

class SpannableStringFactoryTest {

    private val underTest = SpannableStringFactory()

    @Test
    fun createsSpannableStringFromSource() {
        val source = "source"

        val output = underTest.create(source)

        assertEquals(SpannableString(source), output)
    }
}
