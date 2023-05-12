package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.platform.DISPATCHER_IO
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
            viewModelUpdater = GroupsOverviewViewModelUpdater(
                groupItemViewModelMapper = GroupItemViewModelMapper(
                    amountFormatter = get(),
                    resources = androidContext().resources
                ),
                viewModel = parameters.get()
            )
        )
    }
}
