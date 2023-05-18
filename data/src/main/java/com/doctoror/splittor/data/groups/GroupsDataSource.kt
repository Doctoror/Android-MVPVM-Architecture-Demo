package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import kotlinx.coroutines.flow.Flow

internal interface GroupsDataSource {

    suspend fun insert(amount: String, contactNames: List<String>, title: String): Long

    fun observe(): Flow<List<Group>>

    fun observe(id: Long): Flow<Group>

    suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean)
}
