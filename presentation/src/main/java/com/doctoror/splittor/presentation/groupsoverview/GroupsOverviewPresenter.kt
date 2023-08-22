package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.DeleteGroupUseCase
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GroupsOverviewPresenter(
    private val deleteGroupUseCase: DeleteGroupUseCase,
    private val dispatcherIo: CoroutineDispatcher,
    private val observeGroupsUseCase: ObserveGroupsUseCase,
    viewModel: GroupsOverviewViewModel,
    private val viewModelUpdater: GroupsOverviewViewModelUpdater
) : BasePresenter<GroupsOverviewViewModel>(viewModel) {

    override fun onCreate() {
        viewModelScope.launch {
            observeGroupsUseCase()
                .flowOn(dispatcherIo)
                .collect { viewModelUpdater.updateOnGroupsListLoaded(viewModel, it) }
        }
    }

    fun onGroupLongClick(id: Long) {
        viewModelScope.launch { deleteGroupUseCase(id) }
    }
}
