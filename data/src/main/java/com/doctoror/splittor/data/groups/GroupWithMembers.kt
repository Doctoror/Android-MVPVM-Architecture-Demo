package com.doctoror.splittor.data.groups

import androidx.room.Embedded
import androidx.room.Relation

internal data class GroupWithMembers(
    @Embedded val group: GroupEntity,

    @Relation(
        parentColumn = "groupId",
        entityColumn = "groupMemberGroupId"
    )
    val members: List<GroupMemberEntity>
)
