package com.doctoror.splittor.platform.text

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.util.Locale

class AmountFormatterTest {

    private val underTest = AmountFormatter(Locale.US)

    @Test
    fun formatsBigDecimalAmounts() {
        assertEquals("$13.49", underTest.format(BigDecimal("13.49")))
    }

    @Test
    fun formatsStringAmounts() {
        assertEquals("$19.14", underTest.format("19.14"))
    }
}
