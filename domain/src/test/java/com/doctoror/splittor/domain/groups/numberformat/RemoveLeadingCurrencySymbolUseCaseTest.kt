package com.doctoror.splittor.domain.groups.numberformat

import com.doctoror.splittor.domain.numberformat.ProvideCurrencySymbolUseCase
import com.doctoror.splittor.domain.numberformat.RemoveLeadingCurrencySymbolUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoveLeadingCurrencySymbolUseCaseTest {

    private val currencySymbol = "$"

    private val provideCurrencySymbolUseCase: ProvideCurrencySymbolUseCase = mock()

    private val underTest = RemoveLeadingCurrencySymbolUseCase(provideCurrencySymbolUseCase)

    @Before
    fun setup() {
        whenever(provideCurrencySymbolUseCase()).thenReturn(currencySymbol)
    }

    @Test
    fun returnsEmptyIfEmpty() {
        assertEquals("", underTest(""))
    }

    @Test
    fun returnsEmptyIfContainsOnlyCurrencySymbol() {
        assertEquals("", underTest(currencySymbol))
    }

    @Test
    fun returnsUnchangedValueIfDoesNotContainCurrencySymbol() {
        assertEquals("10", underTest("10"))
    }

    @Test
    fun returnsValueWithoutCurrencySymbol() {
        assertEquals("100", underTest("${currencySymbol}100"))
    }

    @Test
    fun returnsValueWithoutCurrencySymbolWhenHasFractionDigits() {
        assertEquals("10.99", underTest("${currencySymbol}10.99"))
    }

    @Test
    fun returnsValueWithoutCurrencySymbolWhenHasFractionDigitsAndGroupingSeparator() {
        assertEquals("1,000.99", underTest("${currencySymbol}1,000.99"))
    }
}
