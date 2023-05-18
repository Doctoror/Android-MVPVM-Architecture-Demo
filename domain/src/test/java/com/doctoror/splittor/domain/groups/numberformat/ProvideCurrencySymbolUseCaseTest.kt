package com.doctoror.splittor.domain.groups.numberformat

import com.doctoror.splittor.domain.numberformat.ProvideCurrencySymbolUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class ProvideCurrencySymbolUseCaseTest {

    @Test
    fun providesForGermany() {
        assertEquals("€", ProvideCurrencySymbolUseCase(Locale.GERMANY)())
    }

    @Test
    fun providesForUs() {
        assertEquals("$", ProvideCurrencySymbolUseCase(Locale.US)())
    }

    @Test
    fun providesForUk() {
        assertEquals("£", ProvideCurrencySymbolUseCase(Locale.UK)())
    }
}
