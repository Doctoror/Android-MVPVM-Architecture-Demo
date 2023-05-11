package com.doctoror.splittor.presentation.groupsoverview

data class GroupItemViewModel(
    val amount: String,
    val allMembersPaid: Boolean,
    val id: Long,
    val members: String,
    val title: String
)
