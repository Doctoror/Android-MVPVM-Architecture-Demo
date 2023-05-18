package com.doctoror.splittor.domain.numberformat

import java.text.DecimalFormatSymbols
import java.util.Locale

class ProvideCurrencySymbolUseCase(private val locale: Locale) {

    private val cached by lazy { DecimalFormatSymbols(locale).currencySymbol }

    operator fun invoke(): String = cached
}
