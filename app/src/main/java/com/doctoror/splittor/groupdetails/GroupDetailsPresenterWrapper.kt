package com.doctoror.splittor.groupdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject

@HiltViewModel
class GroupDetailsPresenterWrapper @Inject constructor(
    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    private val groupsRepository: GroupsRepository,
    private val savedStateHandle: SavedStateHandle
) : PresenterWrapper<GroupDetailsPresenter>() {

    override fun makeWrapped() = GroupDetailsPresenter(
        groupId = savedStateHandle["groupId"]!!,
        observeGroupUseCase = ObserveGroupUseCase(groupsRepository),
        updateMemberPaidStatusUseCase = UpdateMemberPaidStatusUseCase(groupsRepository),
        viewModel = GroupDetailsViewModel(),
        viewModelScope = viewModelScope,
        viewModelUpdater = GroupDetailsViewModelUpdater(
            formatAmountWithCurrencyUseCase = formatAmountWithCurrencyUseCase,
            groupMemberItemViewModelMapper = GroupMemberItemViewModelMapper()
        )
    )
}
