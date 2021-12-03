package com.doctoror.splittor.data.groups

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(group: GroupEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroupMembers(members: List<GroupMemberEntity>): Completable

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME ORDER BY $GROUP_COLUMN_NAME_INSERTED_AT DESC")
    fun observeGroupsWithMembers(): Flow<List<GroupWithMembers>>

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME WHERE $GROUP_COLUMN_NAME_ID = :id")
    fun observeGroupWithMembers(id: Long): Flow<GroupWithMembers>

    @Query("UPDATE $GROUP_MEMBER_TABLE_NAME SET $GROUP_MEMBER_COLUMN_PAID = :paid WHERE $GROUP_MEMBER_COLUMN_NAME_ID = :id")
    suspend fun updateMemberPaidStatus(id: Long, paid: Boolean)
}
