package com.doctoror.splittor.groupdetails

import androidx.lifecycle.SavedStateHandle
import com.doctoror.splittor.domain.groups.GroupsRepository
import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import com.doctoror.splittor.framework.PresenterWrapper
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsPresenter
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModel
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModelUpdater
import com.doctoror.splittor.presentation.groupdetails.GroupMemberItemViewModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class GroupDetailsPresenterWrapper @Inject constructor(
    formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    groupsRepository: GroupsRepository,
    savedStateHandle: SavedStateHandle
) : PresenterWrapper<GroupDetailsPresenter>(
    GroupDetailsPresenter(
        groupId = savedStateHandle["groupId"]!!,
        observeGroupUseCase = ObserveGroupUseCase(groupsRepository),
        updateMemberPaidStatusUseCase = UpdateMemberPaidStatusUseCase(groupsRepository),
        viewModel = GroupDetailsViewModel(),
        viewModelUpdater = GroupDetailsViewModelUpdater(
            formatAmountWithCurrencyUseCase = formatAmountWithCurrencyUseCase,
            groupMemberItemViewModelMapper = GroupMemberItemViewModelMapper()
        )
    )
)