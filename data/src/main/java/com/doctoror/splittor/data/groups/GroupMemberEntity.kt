package com.doctoror.splittor.data.groups

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val GROUP_MEMBER_TABLE_NAME = "groupMembers"

const val GROUP_MEMBER_COLUMN_NAME_ID = "groupMemberId"
const val GROUP_MEMBER_COLUMN_NAME_GROUP_ID = "groupMemberGroupId"
const val GROUP_MEMBER_COLUMN_PAID = "groupMemberPaid"

@Entity(tableName = GROUP_MEMBER_TABLE_NAME)
internal data class GroupMemberEntity(
    @ColumnInfo(name = GROUP_MEMBER_COLUMN_NAME_ID) @PrimaryKey(autoGenerate = true) val groupMemberId: Long,
    @ColumnInfo(name = GROUP_MEMBER_COLUMN_NAME_GROUP_ID) val groupMemberGroupId: Long,
    @ColumnInfo(name = GROUP_MEMBER_COLUMN_PAID) val groupMemberPaid: Boolean,
    @ColumnInfo(name = "groupMemberName") val groupMemberName: String
)
