package com.doctoror.splittor.domain.numberformat

import java.math.BigDecimal

interface FormatAmountWithCurrencyUseCase {

    fun format(amount: BigDecimal): String
}
