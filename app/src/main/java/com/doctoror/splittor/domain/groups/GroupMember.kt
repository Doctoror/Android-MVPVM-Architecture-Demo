package com.doctoror.splittor.domain.groups

interface GroupMember {
    val contactId: Long
    val paid: Boolean
    val title: String
}
