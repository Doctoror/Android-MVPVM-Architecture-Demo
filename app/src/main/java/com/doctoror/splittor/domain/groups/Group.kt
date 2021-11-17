package com.doctoror.splittor.domain.groups

interface Group {
    val id: Long
    val members: List<GroupMember>
    val sum: String
    val title: String
}
