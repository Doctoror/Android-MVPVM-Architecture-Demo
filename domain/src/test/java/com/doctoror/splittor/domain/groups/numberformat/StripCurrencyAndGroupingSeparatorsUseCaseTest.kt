package com.doctoror.splittor.domain.groups.numberformat

import com.doctoror.splittor.domain.numberformat.RemoveGroupingSeparatorsUseCase
import com.doctoror.splittor.domain.numberformat.RemoveLeadingCurrencySymbolUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class StripCurrencyAndGroupingSeparatorsUseCaseTest {

    private val removeGroupingSeparatorsUseCase: RemoveGroupingSeparatorsUseCase = mock()
    private val removeLeadingCurrencySymbolUseCase: RemoveLeadingCurrencySymbolUseCase = mock()

    private val underTest = StripCurrencyAndGroupingSeparatorsUseCase(
        removeGroupingSeparatorsUseCase,
        removeLeadingCurrencySymbolUseCase
    )

    @Test
    fun stripsCurrencyAndGroupingSeparators() {
        val source = "source"
        val sourceWithoutLeadingCurrencySymbol = "sourceWithoutLeadingCurrencySymbol"
        whenever(removeLeadingCurrencySymbolUseCase(source))
            .thenReturn(sourceWithoutLeadingCurrencySymbol)

        val sourceWithoutGroupingSeparators = "sourceWithoutGroupingSeparators"
        whenever(removeGroupingSeparatorsUseCase(sourceWithoutLeadingCurrencySymbol))
            .thenReturn(sourceWithoutGroupingSeparators)

        val output = underTest(source)

        assertEquals(sourceWithoutGroupingSeparators, output)
    }
}
