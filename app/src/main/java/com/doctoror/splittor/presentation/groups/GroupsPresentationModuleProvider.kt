package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.platform.SCHEDULER_IO
import com.doctoror.splittor.platform.SCHEDULER_MAIN_THREAD
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideGroupsPresentationModule() = module {

    viewModel {
        GroupsViewModel()
    }

    viewModel { parameters ->
        GroupsPresenter(
            observeGroupsUseCase = ObserveGroupsUseCase(groupsRepository = get()),
            schedulerIo = get(named(SCHEDULER_IO)),
            schedulerMainThread = get(named(SCHEDULER_MAIN_THREAD)),
            viewModelUpdater = GroupsViewModelUpdater(viewModel = parameters.get())
        )
    }
}
