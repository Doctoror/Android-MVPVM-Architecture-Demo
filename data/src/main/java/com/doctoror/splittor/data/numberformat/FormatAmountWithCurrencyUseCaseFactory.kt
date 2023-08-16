package com.doctoror.splittor.data.numberformat

import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import java.util.Locale

class FormatAmountWithCurrencyUseCaseFactory {

    fun newInstance(locale: Locale): FormatAmountWithCurrencyUseCase =
        FormatAmountWithCurrencyUseCaseImpl(locale)
}