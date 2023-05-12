package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group

class GroupsOverviewViewModelUpdater(
    private val groupItemViewModelMapper: GroupItemViewModelMapper,
    private val viewModel: GroupsOverviewViewModel
) {

    fun updateOnGroupsListLoaded(groups: List<Group>) {
        viewModel.groups.set(groups.map(groupItemViewModelMapper::map))
        viewModel.displayedChildId.set(
            if (groups.isEmpty()) {
                R.id.fragmentGroupsEmpty
            } else {
                R.id.fragmentGroupsContent
            }
        )
    }
}
