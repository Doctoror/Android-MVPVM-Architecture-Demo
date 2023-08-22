package com.doctoror.splittor.data.groups

import androidx.room.Entity
import androidx.room.PrimaryKey

const val GROUP_TABLE_NAME = "groups"

@Entity(tableName = GROUP_TABLE_NAME)
internal data class GroupEntity(
    val groupAmount: String,
    @PrimaryKey(autoGenerate = true) val groupId: Long,
    val groupTitle: String,
    val groupInsertedAt: Long
)
