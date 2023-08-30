package com.doctoror.splittor.presentation.groupdetails

data class GroupMemberItemViewModel(
    val amount: String,
    val id: Long,
    val name: String,
    val paid: Boolean
) : Comparable<GroupMemberItemViewModel> {

    override fun compareTo(other: GroupMemberItemViewModel) = name.compareTo(other.name)
}
