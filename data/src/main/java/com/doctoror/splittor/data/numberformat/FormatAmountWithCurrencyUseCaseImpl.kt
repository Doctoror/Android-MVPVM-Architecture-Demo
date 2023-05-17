package com.doctoror.splittor.data.numberformat

import android.icu.text.NumberFormat
import android.icu.util.Currency
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import java.math.BigDecimal
import java.util.Locale

class FormatAmountWithCurrencyUseCaseImpl(
    private val locale: Locale
) : FormatAmountWithCurrencyUseCase {

    override fun format(amount: BigDecimal): String = NumberFormat
        .getCurrencyInstance(locale)
        .apply { currency = Currency.getInstance("USD") } // TODO should not hardcode
        .format(amount)
}
