package com.doctoror.splittor.di

import com.doctoror.splittor.groupdetails.GroupDetailsPresenterWrapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideGroupDetailsModule() = module {

    viewModel { parameters ->
        GroupDetailsPresenterWrapper(
            formatAmountWithCurrencyUseCase = get(),
            groupId = parameters.get(),
            groupsRepository = get()
        )
    }
}
