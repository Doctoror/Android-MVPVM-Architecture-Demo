package com.doctoror.splittor.presentation.groupdetails

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GroupDetailsViewModel : ViewModel() {

    val amount = mutableStateOf("")

    val members = mutableStateListOf<GroupMemberItemViewModel>()

    val title = mutableStateOf("")
}
