package com.doctoror.splittor.domain.numberformat

import java.text.DecimalFormatSymbols
import java.util.Locale

class RemoveGroupingSeparatorsUseCase(private val locale: Locale) {

    operator fun invoke(source: String): String =
        source.replace(DecimalFormatSymbols(locale).groupingSeparator.toString(), "")
}
