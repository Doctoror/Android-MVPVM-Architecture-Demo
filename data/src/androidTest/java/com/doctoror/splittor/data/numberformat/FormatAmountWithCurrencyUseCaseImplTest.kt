package com.doctoror.splittor.data.numberformat

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.util.Locale

class FormatAmountWithCurrencyUseCaseImplTest {

    private val underTest = FormatAmountWithCurrencyUseCaseImpl(Locale.US)

    @Test
    fun formatsZeroAmount() {
        assertEquals("$0.00", underTest.format(BigDecimal.ZERO))
    }

    @Test
    fun formatsAmountWith1FractionDigit() {
        assertEquals("$13.40", underTest.format(BigDecimal("13.4")))
    }

    @Test
    fun formatsAmountWith2FractionDigits() {
        assertEquals("$99.68", underTest.format(BigDecimal("99.68")))
    }

    @Test
    fun formatsAmountWithMoreThan2FractionDigits() {
        assertEquals("$200.13", underTest.format(BigDecimal("200.134")))
    }

    @Test
    fun formatsAmountWithMoreThanThousandsSeparator() {
        assertEquals("$150,000.99", underTest.format(BigDecimal("150000.99")))
    }
}
