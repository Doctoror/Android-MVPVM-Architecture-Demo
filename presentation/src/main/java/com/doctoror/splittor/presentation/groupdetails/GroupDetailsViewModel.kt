package com.doctoror.splittor.presentation.groupdetails

import kotlinx.coroutines.flow.MutableStateFlow

class GroupDetailsViewModel {

    val amount = MutableStateFlow("")

    val members = MutableStateFlow<List<GroupMemberItemViewModel>>(emptyList())

    val title = MutableStateFlow("")
}
