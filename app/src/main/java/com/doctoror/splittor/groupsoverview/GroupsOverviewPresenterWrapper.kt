package com.doctoror.splittor.groupsoverview

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.domain.groups.DeleteGroupUseCase
import com.doctoror.splittor.domain.groups.GroupsRepository
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import com.doctoror.splittor.framework.PresenterWrapper
import com.doctoror.splittor.presentation.groupsoverview.GroupItemViewModelMapper
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewPresenter
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModel
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModelUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupsOverviewPresenterWrapper @Inject constructor(
    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    private val groupsRepository: GroupsRepository,
    private val resources: Resources
) : PresenterWrapper<GroupsOverviewPresenter>() {

    override fun makeWrapped() = GroupsOverviewPresenter(
        deleteGroupUseCase = DeleteGroupUseCase(groupsRepository),
        observeGroupsUseCase = ObserveGroupsUseCase(groupsRepository),
        viewModel = GroupsOverviewViewModel(),
        viewModelScope = viewModelScope,
        viewModelUpdater = GroupsOverviewViewModelUpdater(
            groupItemViewModelMapper = GroupItemViewModelMapper(
                formatAmountWithCurrencyUseCase = formatAmountWithCurrencyUseCase,
                resources = resources
            )
        )
    )
}
