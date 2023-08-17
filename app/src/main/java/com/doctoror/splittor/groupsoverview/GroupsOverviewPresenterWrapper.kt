package com.doctoror.splittor.groupsoverview

import android.content.res.Resources
import com.doctoror.splittor.domain.groups.GroupsRepository
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import com.doctoror.splittor.framework.PresenterWrapper
import com.doctoror.splittor.presentation.groupsoverview.GroupItemViewModelMapper
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewPresenter
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModel
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModelUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class GroupsOverviewPresenterWrapper @Inject constructor(
    formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    groupsRepository: GroupsRepository,
    resources: Resources
) : PresenterWrapper<GroupsOverviewPresenter>(
    GroupsOverviewPresenter(
        dispatcherIo = Dispatchers.IO,
        observeGroupsUseCase = ObserveGroupsUseCase(groupsRepository),
        viewModel = GroupsOverviewViewModel(),
        viewModelUpdater = GroupsOverviewViewModelUpdater(
            groupItemViewModelMapper = GroupItemViewModelMapper(
                formatAmountWithCurrencyUseCase = formatAmountWithCurrencyUseCase,
                resources = resources
            )
        )
    )
)