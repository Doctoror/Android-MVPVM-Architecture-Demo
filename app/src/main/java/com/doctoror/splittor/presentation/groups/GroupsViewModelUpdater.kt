package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group

class GroupsViewModelUpdater(private val viewModel: GroupsViewModel) {

    fun updateOnGroupsListLoaded(groups: List<Group>) {
        viewModel.displayedChildId.set(
            if (groups.isEmpty()) {
                R.id.fragmentGroupsEmpty
            } else {
                R.id.fragmentGroupsContent
            }
        )
    }
}
