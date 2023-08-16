package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.GroupMember

internal class GroupMemberMapper {

    fun transform(input: GroupMemberEntity) = GroupMember(
        id = input.groupMemberId,
        name = input.groupMemberName,
        paid = input.groupMemberPaid
    )
}