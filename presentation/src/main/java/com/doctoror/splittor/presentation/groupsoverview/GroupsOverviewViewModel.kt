package com.doctoror.splittor.presentation.groupsoverview

import kotlinx.coroutines.flow.MutableStateFlow

class GroupsOverviewViewModel {

    val groups = MutableStateFlow<List<GroupItemViewModel>>(emptyList())

    val viewType = MutableStateFlow(ViewType.LOADING)

    enum class ViewType {

        LOADING,
        CONTENT,
        EMPTY
    }
}
