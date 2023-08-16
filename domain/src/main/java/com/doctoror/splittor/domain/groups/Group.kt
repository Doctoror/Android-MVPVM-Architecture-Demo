package com.doctoror.splittor.domain.groups

data class Group(
    val allMembersPaid: Boolean,
    val amount: String,
    val id: Long,
    val insertedAt: Long,
    val members: List<GroupMember>,
    val title: String
)
