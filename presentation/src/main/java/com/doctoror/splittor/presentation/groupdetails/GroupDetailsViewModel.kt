package com.doctoror.splittor.presentation.groupdetails

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class GroupDetailsViewModel {

    val amount = mutableStateOf("")

    val members = mutableStateListOf<GroupMemberItemViewModel>()

    val title = mutableStateOf("")
}
