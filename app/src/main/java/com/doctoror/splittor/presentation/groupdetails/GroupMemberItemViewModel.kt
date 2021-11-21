package com.doctoror.splittor.presentation.groupdetails

data class GroupMemberItemViewModel(
    val amount: CharSequence,
    val id: Long,
    val name: CharSequence,
    val paid: Boolean
)
