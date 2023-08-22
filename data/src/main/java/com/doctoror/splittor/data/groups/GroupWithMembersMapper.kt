package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupMember

internal class GroupWithMembersMapper {

    fun transform(input: GroupWithMembers) = Group(
        allMembersPaid = input.members.all { it.groupMemberPaid },
        amount = input.group.groupAmount,
        id = input.group.groupId,
        insertedAt = input.group.groupInsertedAt,
        members = input.members.map { transform(it) },
        title = input.group.groupTitle
    )

    private fun transform(input: GroupMemberEntity) = GroupMember(
        id = input.groupMemberId,
        name = input.groupMemberName,
        paid = input.groupMemberPaid
    )
}