package com.doctoror.splittor.di

import androidx.lifecycle.SavedStateHandle
import com.doctoror.splittor.addgroup.AddGroupPresenterWrapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideAddGroupModule() = module {

    viewModel { (savedStateHandle: SavedStateHandle) ->
        AddGroupPresenterWrapper(
            getContactDetailsUseCase = get(),
            groupsRepository = get(),
            savedStateHandle,
            stripCurrencyAndGroupingSeparatorsUseCase = get(),
        )
    }
}
