package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.Group

class GroupsOverviewViewModelUpdater(
    private val groupItemViewModelMapper: GroupItemViewModelMapper,
    private val viewModel: GroupsOverviewViewModel
) {

    fun updateOnGroupsListLoaded(groups: List<Group>) {
        viewModel.groups.clear()
        viewModel.groups.addAll(groups.map(groupItemViewModelMapper::map))

        viewModel.viewType.value = if (groups.isEmpty()) {
            GroupsOverviewViewModel.ViewType.EMPTY
        } else {
            GroupsOverviewViewModel.ViewType.CONTENT
        }
    }
}
