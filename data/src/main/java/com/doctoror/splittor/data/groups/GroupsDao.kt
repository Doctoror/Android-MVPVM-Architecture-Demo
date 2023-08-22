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
    @Query("SELECT * FROM $GROUP_TABLE_NAME ORDER BY groupInsertedAt DESC")
    fun observeGroupsWithMembers(): Flow<List<GroupWithMembers>>

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME WHERE groupId = :id")
    fun observeGroupWithMembers(id: Long): Flow<GroupWithMembers>

    @Query("UPDATE $GROUP_MEMBER_TABLE_NAME SET groupMemberPaid = :paid WHERE groupMemberId = :id")
    suspend fun updateMemberPaidStatus(id: Long, paid: Boolean)
}
