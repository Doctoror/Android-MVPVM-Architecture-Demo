package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group

class GroupsViewModelUpdater(
    private val groupItemViewModelMapper: GroupItemViewModelMapper,
    private val viewModel: GroupsViewModel
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
