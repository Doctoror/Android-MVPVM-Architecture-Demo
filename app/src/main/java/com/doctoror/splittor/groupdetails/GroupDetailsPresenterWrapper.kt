package com.doctoror.splittor.groupdetails

import com.doctoror.splittor.domain.groups.GroupsRepository
import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import com.doctoror.splittor.framework.PresenterWrapper
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsPresenter
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModel
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModelUpdater
import com.doctoror.splittor.presentation.groupdetails.GroupMemberItemViewModelMapper
import kotlinx.coroutines.Dispatchers

class GroupDetailsPresenterWrapper(
    formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    groupId: Long,
    groupsRepository: GroupsRepository
) : PresenterWrapper<GroupDetailsPresenter>(
    GroupDetailsPresenter(
        dispatcherIo = Dispatchers.IO,
        groupId = groupId,
        observeGroupUseCase = ObserveGroupUseCase(groupsRepository),
        updateMemberPaidStatusUseCase = UpdateMemberPaidStatusUseCase(groupsRepository),
        viewModel = GroupDetailsViewModel(),
        viewModelUpdater = GroupDetailsViewModelUpdater(
            formatAmountWithCurrencyUseCase = formatAmountWithCurrencyUseCase,
            groupMemberItemViewModelMapper = GroupMemberItemViewModelMapper()
        )
    )
)