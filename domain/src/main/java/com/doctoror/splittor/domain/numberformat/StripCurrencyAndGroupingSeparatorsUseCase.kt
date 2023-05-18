package com.doctoror.splittor.domain.numberformat

class StripCurrencyAndGroupingSeparatorsUseCase(
    private val removeGroupingSeparatorsUseCase: RemoveGroupingSeparatorsUseCase,
    private val removeLeadingCurrencySymbolUseCase: RemoveLeadingCurrencySymbolUseCase
) {

    operator fun invoke(source: String): String = removeGroupingSeparatorsUseCase(
        removeLeadingCurrencySymbolUseCase(source)
    )
}
