package com.doctoror.splittor.di

import com.doctoror.splittor.groupsoverview.GroupsOverviewPresenterWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideGroupsOverviewPresentationModule() = module {

    viewModel {
        GroupsOverviewPresenterWrapper(
            formatAmountWithCurrencyUseCase = get(),
            groupsRepository = get(),
            resources = androidContext().resources
        )
    }
}
