package com.doctoror.splittor.presentation.groupsoverview

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GroupsOverviewViewModel : ViewModel() {

    val groups = mutableStateListOf<GroupItemViewModel>()

    val viewType = mutableStateOf(ViewType.LOADING)

    enum class ViewType {

        LOADING,
        CONTENT,
        EMPTY
    }
}
