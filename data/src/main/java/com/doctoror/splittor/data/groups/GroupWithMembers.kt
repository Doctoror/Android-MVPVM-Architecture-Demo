package com.doctoror.splittor.data.groups

import androidx.room.Embedded
import androidx.room.Relation

internal data class GroupWithMembers(
    @Embedded val group: GroupEntity,

    @Relation(
        parentColumn = GROUP_COLUMN_NAME_ID,
        entityColumn = GROUP_MEMBER_COLUMN_NAME_GROUP_ID
    )
    val members: List<GroupMemberEntity>
)
