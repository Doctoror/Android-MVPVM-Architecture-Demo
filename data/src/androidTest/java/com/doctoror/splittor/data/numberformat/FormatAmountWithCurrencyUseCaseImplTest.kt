package com.doctoror.splittor.data.numberformat

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.util.Locale

class FormatAmountWithCurrencyUseCaseImplTest {

    private val underTest = FormatAmountWithCurrencyUseCaseImpl(Locale.US)

    @Test
    fun formatsZeroAmount() {
        assertEquals("$0.00", underTest(BigDecimal.ZERO))
    }

    @Test
    fun formatsAmountWith1FractionDigit() {
        assertEquals("$13.40", underTest(BigDecimal("13.4")))
    }

    @Test
    fun formatsAmountWith2FractionDigits() {
        assertEquals("$99.68", underTest(BigDecimal("99.68")))
    }

    @Test
    fun formatsAmountWithMoreThan2FractionDigits() {
        assertEquals("$200.13", underTest(BigDecimal("200.134")))
    }

    @Test
    fun formatsAmountWithMoreThanThousandsSeparator() {
        assertEquals("$150,000.99", underTest(BigDecimal("150000.99")))
    }
}
