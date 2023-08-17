package com.doctoror.splittor.di

import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsPresenter
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModel
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModelUpdater
import com.doctoror.splittor.presentation.groupdetails.GroupMemberItemViewModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideGroupDetailsModule() = module {

    viewModel { parameters ->
        GroupDetailsPresenter(
            dispatcherIo = get(named(DISPATCHER_IO)),
            groupId = parameters.get(),
            observeGroupUseCase = ObserveGroupUseCase(groupsRepository = get()),
            updateMemberPaidStatusUseCase = UpdateMemberPaidStatusUseCase(groupsRepository = get()),
            viewModel = GroupDetailsViewModel(),
            viewModelUpdater = GroupDetailsViewModelUpdater(
                formatAmountWithCurrencyUseCase = get(),
                groupMemberItemViewModelMapper = GroupMemberItemViewModelMapper()
            )
        )
    }
}
