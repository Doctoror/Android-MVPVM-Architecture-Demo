package com.doctoror.splittor.di

import com.doctoror.splittor.data.numberformat.FormatAmountWithCurrencyUseCaseFactory
import com.doctoror.splittor.domain.numberformat.ProvideCurrencySymbolUseCase
import com.doctoror.splittor.domain.numberformat.RemoveGroupingSeparatorsUseCase
import com.doctoror.splittor.domain.numberformat.RemoveLeadingCurrencySymbolUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class ModuleSingletonNumberFormat {

    @Provides
    fun provideCurrencySymbolUseCase(locale: Locale) = ProvideCurrencySymbolUseCase(locale)

    @Provides
    fun stripCurrencyAndGroupingSeparatorsUseCase(
        locale: Locale,
        provideCurrencySymbolUseCase: ProvideCurrencySymbolUseCase
    ) = StripCurrencyAndGroupingSeparatorsUseCase(
        RemoveGroupingSeparatorsUseCase(locale),
        RemoveLeadingCurrencySymbolUseCase(provideCurrencySymbolUseCase)
    )

    @Provides
    fun formatAmountWithCurrencyUseCase(locale: Locale) = FormatAmountWithCurrencyUseCaseFactory()
        .newInstance(locale)
}