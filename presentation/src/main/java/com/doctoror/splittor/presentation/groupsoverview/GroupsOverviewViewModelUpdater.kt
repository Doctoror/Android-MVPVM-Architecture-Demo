package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.Group

class GroupsOverviewViewModelUpdater(
    private val groupItemViewModelMapper: GroupItemViewModelMapper
) {

    suspend fun updateOnGroupsListLoaded(viewModel: GroupsOverviewViewModel, groups: List<Group>) {
        viewModel.groups.emit(groups.map(groupItemViewModelMapper::map))

        viewModel.viewType.value = if (groups.isEmpty()) {
            GroupsOverviewViewModel.ViewType.EMPTY
        } else {
            GroupsOverviewViewModel.ViewType.CONTENT
        }
    }
}
