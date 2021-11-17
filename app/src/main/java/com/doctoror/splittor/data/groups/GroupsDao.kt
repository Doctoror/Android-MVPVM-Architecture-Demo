package com.doctoror.splittor.data.groups

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Observable

@Dao
interface GroupsDao {

    @Transaction
    @Query("SELECT * FROM $GROUP_TABLE_NAME")
    fun observeGroupsWithMembers(): Observable<List<GroupWithMembers>>
}
