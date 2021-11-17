package com.doctoror.splittor.data.groups

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.doctoror.splittor.domain.groups.Group

data class GroupWithMembers(
    @Embedded val group: GroupEntity,

    @Relation(
        parentColumn = GROUP_COLUMN_NAME_ID,
        entityColumn = GROUP_MEMBER_COLUMN_NAME_GROUP_ID
    )
    override val members: List<GroupMemberEntity>
) : Group {

    @Ignore
    override val id = group.groupId

    @Ignore
    override val sum = group.groupSum

    @Ignore
    override val title = group.groupTitle
}
