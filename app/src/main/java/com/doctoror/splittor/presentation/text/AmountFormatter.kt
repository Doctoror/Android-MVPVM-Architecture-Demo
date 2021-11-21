package com.doctoror.splittor.presentation.text

import android.icu.text.NumberFormat
import android.icu.util.Currency
import java.math.BigDecimal
import java.util.*

class AmountFormatter(private val locale: Locale) {

    fun format(amount: String): CharSequence = NumberFormat
        .getCurrencyInstance(locale)
        .apply { currency = Currency.getInstance("USD") } // TODO should not hardcode
        .format(BigDecimal(amount))
}
