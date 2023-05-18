package com.doctoror.splittor.domain.numberformat

class RemoveLeadingCurrencySymbolUseCase(
    private val provideCurrencySymbolUseCase: ProvideCurrencySymbolUseCase
) {

    operator fun invoke(source: String): String {
        val currencySymbol = provideCurrencySymbolUseCase()

        return if (source.startsWith(currencySymbol)) {
            source.substring(currencySymbol.length)
        } else {
            source
        }
    }
}
