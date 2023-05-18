package com.doctoror.splittor.data.groups

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val GROUP_TABLE_NAME = "groups"

const val GROUP_COLUMN_NAME_ID = "groupId"
const val GROUP_COLUMN_NAME_INSERTED_AT = "insertedAt"

@Entity(tableName = GROUP_TABLE_NAME)
internal data class GroupEntity(
    @ColumnInfo(name = "groupAmount") val groupAmount: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = GROUP_COLUMN_NAME_ID) val groupId: Long,
    @ColumnInfo(name = "groupTitle") val groupTitle: String,
    @ColumnInfo(name = GROUP_COLUMN_NAME_INSERTED_AT) val insertedAt: Long
)
