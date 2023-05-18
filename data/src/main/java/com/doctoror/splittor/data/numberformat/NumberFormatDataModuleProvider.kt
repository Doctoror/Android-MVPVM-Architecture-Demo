package com.doctoror.splittor.data.numberformat

import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import com.doctoror.splittor.domain.numberformat.ProvideCurrencySymbolUseCase
import com.doctoror.splittor.domain.numberformat.RemoveGroupingSeparatorsUseCase
import com.doctoror.splittor.domain.numberformat.RemoveLeadingCurrencySymbolUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import org.koin.dsl.module

fun provideNumberFormatDataModule() = module {

    factory<FormatAmountWithCurrencyUseCase> {
        FormatAmountWithCurrencyUseCaseImpl(locale = get())
    }

    factory { ProvideCurrencySymbolUseCase(locale = get()) }

    factory {
        StripCurrencyAndGroupingSeparatorsUseCase(
            RemoveGroupingSeparatorsUseCase(locale = get()),
            RemoveLeadingCurrencySymbolUseCase(provideCurrencySymbolUseCase = get())
        )
    }
}
