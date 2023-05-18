package com.doctoror.splittor.data.groups

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
internal interface GroupsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupMembers(members: List<GroupMemberEntity>)

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME ORDER BY $GROUP_COLUMN_NAME_INSERTED_AT DESC")
    fun observeGroupsWithMembers(): Flow<List<GroupWithMembers>>

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME WHERE $GROUP_COLUMN_NAME_ID = :id")
    fun observeGroupWithMembers(id: Long): Flow<GroupWithMembers>

    @Query("UPDATE $GROUP_MEMBER_TABLE_NAME SET $GROUP_MEMBER_COLUMN_PAID = :paid WHERE $GROUP_MEMBER_COLUMN_NAME_ID = :id")
    suspend fun updateMemberPaidStatus(id: Long, paid: Boolean)
}
