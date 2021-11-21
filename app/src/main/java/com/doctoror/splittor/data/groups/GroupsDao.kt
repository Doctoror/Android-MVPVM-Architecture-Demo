package com.doctoror.splittor.data.groups

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface GroupsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(group: GroupEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroupMembers(members: List<GroupMemberEntity>): Completable

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME")
    fun observeGroupsWithMembers(): Observable<List<GroupWithMembers>>

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME WHERE $GROUP_COLUMN_NAME_ID = :id")
    fun observeGroupWithMembers(id: Long): Observable<GroupWithMembers>

    @Query("UPDATE $GROUP_MEMBER_TABLE_NAME SET $GROUP_MEMBER_COLUMN_PAID = :paid WHERE $GROUP_MEMBER_COLUMN_NAME_ID = :id")
    fun updateMemberPaidStatus(id: Long, paid: Boolean): Completable
}
