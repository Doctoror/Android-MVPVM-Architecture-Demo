package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.GroupMember

class GroupMemberItemViewModelMapper {

    fun map(amountPerMember: String, groupMember: GroupMember) = GroupMemberItemViewModel(
        amount = amountPerMember,
        id = groupMember.id,
        name = groupMember.name,
        paid = groupMember.paid
    )
}
