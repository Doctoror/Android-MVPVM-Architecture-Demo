package com.doctoror.splittor.domain.numberformat

import java.math.BigDecimal

interface FormatAmountWithCurrencyUseCase {

    operator fun invoke(amount: BigDecimal): String
}
