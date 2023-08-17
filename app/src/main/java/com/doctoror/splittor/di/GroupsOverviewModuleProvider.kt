package com.doctoror.splittor.di

import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.groupsoverview.GroupItemViewModelMapper
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewPresenter
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModel
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModelUpdater
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideGroupsOverviewPresentationModule() = module {

    viewModel {
        GroupsOverviewViewModel()
    }

    viewModel { parameters ->
        GroupsOverviewPresenter(
            dispatcherIo = get(named(DISPATCHER_IO)),
            observeGroupsUseCase = ObserveGroupsUseCase(groupsRepository = get()),
            viewModel = parameters.get(),
            viewModelUpdater = GroupsOverviewViewModelUpdater(
                groupItemViewModelMapper = GroupItemViewModelMapper(
                    formatAmountWithCurrencyUseCase = get(),
                    resources = androidContext().resources
                )
            )
        )
    }
}
