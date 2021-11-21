package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.platform.SCHEDULER_IO
import com.doctoror.splittor.platform.SCHEDULER_MAIN_THREAD
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideGroupDetailsModule() = module {

    viewModel { GroupDetailsViewModel() }

    viewModel { parameters ->
        GroupDetailsPresenter(
            groupId = parameters.get(),
            observeGroupUseCase = ObserveGroupUseCase(groupsRepository = get()),
            schedulerIo = get(named(SCHEDULER_IO)),
            schedulerMainThread = get(named(SCHEDULER_MAIN_THREAD)),
            viewModelUpdater = GroupDetailsViewModelUpdater(
                amountFormatter = get(),
                groupMemberItemViewModelMapper = GroupMemberItemViewModelMapper(),
                viewModel = parameters.get()
            )
        )
    }
}
