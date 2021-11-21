package com.doctoror.splittor.domain.groups

interface Group {
    val amount: String
    val id: Long
    val members: List<GroupMember>
    val title: String
}
