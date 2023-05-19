package com.doctoror.splittor.presentation.groupsoverview

import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GroupsOverviewPresenter(
    private val dispatcherIo: CoroutineDispatcher,
    private val observeGroupsUseCase: ObserveGroupsUseCase,
    private val viewModelUpdater: GroupsOverviewViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        viewModelScope.launch {
            observeGroupsUseCase()
                .flowOn(dispatcherIo)
                .collect { viewModelUpdater.updateOnGroupsListLoaded(it) }
        }
    }
}
