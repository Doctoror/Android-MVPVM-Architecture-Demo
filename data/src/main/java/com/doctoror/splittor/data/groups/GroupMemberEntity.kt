package com.doctoror.splittor.data.groups

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

const val GROUP_MEMBER_TABLE_NAME = "groupMembers"

@Entity(
    tableName = GROUP_MEMBER_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["groupId"],
            childColumns = ["groupMemberGroupId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
internal data class GroupMemberEntity(
    @PrimaryKey(autoGenerate = true) val groupMemberId: Long,
    val groupMemberGroupId: Long,
    val groupMemberPaid: Boolean,
    val groupMemberName: String
)
