package com.doctoror.splittor.data.groups

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.doctoror.splittor.domain.groups.GroupMember

const val GROUP_MEMBER_TABLE_NAME = "groupMembers"

const val GROUP_MEMBER_COLUMN_NAME_CONTACT_ID = "groupMemberContactId"
const val GROUP_MEMBER_COLUMN_NAME_GROUP_ID = "groupMemberGroupId"
const val GROUP_MEMBER_COLUMN_PAID = "groupMemberPaid"

@Entity(
    primaryKeys = [GROUP_MEMBER_COLUMN_NAME_CONTACT_ID, GROUP_MEMBER_COLUMN_NAME_GROUP_ID],
    tableName = GROUP_MEMBER_TABLE_NAME
)
data class GroupMemberEntity(
    @ColumnInfo(name = GROUP_MEMBER_COLUMN_NAME_CONTACT_ID) val groupMemberContactId: Long,
    @ColumnInfo(name = GROUP_MEMBER_COLUMN_NAME_GROUP_ID) val groupMemberGroupId: Long,
    @ColumnInfo(name = GROUP_MEMBER_COLUMN_PAID) val groupMemberPaid: Boolean,
    @ColumnInfo(name = "groupMemberTitle") val groupMemberTitle: String
) : GroupMember {

    @Ignore
    override val contactId = groupMemberContactId

    @Ignore
    override val paid = groupMemberPaid

    @Ignore
    override val title = groupMemberTitle
}
