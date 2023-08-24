package com.doctoror.splittor.domain.numberformat

import java.text.DecimalFormatSymbols
import java.util.Locale

class RemoveGroupingSeparatorsUseCase(private val locale: Locale) {

    private val groupingSeparator by lazy {
        DecimalFormatSymbols(locale).groupingSeparator.toString()
    }

    operator fun invoke(source: String): String = source.replace(groupingSeparator, "")
}
