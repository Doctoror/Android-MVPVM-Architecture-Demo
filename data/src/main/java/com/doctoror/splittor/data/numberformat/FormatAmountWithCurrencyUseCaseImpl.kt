package com.doctoror.splittor.data.numberformat

import android.annotation.TargetApi
import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Build
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import java.math.BigDecimal
import java.util.Locale

internal class FormatAmountWithCurrencyUseCaseImpl(
    private val locale: Locale
) : FormatAmountWithCurrencyUseCase {

    override fun format(amount: BigDecimal): String = with(Currency.getInstance(locale)) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            formatApiLevel30(amount, this)
        } else {
            formatLegacy(amount, this)
        }
    }

    private fun formatLegacy(amount: BigDecimal, currency: Currency): String = NumberFormat
        .getCurrencyInstance(locale)
        .apply { setCurrency(currency) }
        .format(amount)

    @TargetApi(Build.VERSION_CODES.R)
    private fun formatApiLevel30(amount: BigDecimal, currency: Currency): String = NumberFormatter
        .withLocale(locale)
        .unit(currency)
        .format(amount)
        .toString()
}
