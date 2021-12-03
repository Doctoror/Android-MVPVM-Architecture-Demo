package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.platform.DISPATCHER_IO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideGroupDetailsModule() = module {

    viewModel { GroupDetailsViewModel() }

    viewModel { parameters ->
        GroupDetailsPresenter(
            dispatcherIo = get(named(DISPATCHER_IO)),
            groupId = parameters.get(),
            observeGroupUseCase = ObserveGroupUseCase(groupsRepository = get()),
            updateMemberPaidStatusUseCase = UpdateMemberPaidStatusUseCase(groupsRepository = get()),
            viewModelUpdater = GroupDetailsViewModelUpdater(
                amountFormatter = get(),
                groupMemberItemViewModelMapper = GroupMemberItemViewModelMapper(),
                viewModel = parameters.get()
            )
        )
    }
}
