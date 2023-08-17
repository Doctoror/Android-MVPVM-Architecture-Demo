package com.doctoror.splittor.presentation.groupsoverview

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class GroupsOverviewViewModel {

    val groups = mutableStateListOf<GroupItemViewModel>()

    val viewType = mutableStateOf(ViewType.LOADING)

    enum class ViewType {

        LOADING,
        CONTENT,
        EMPTY
    }
}
