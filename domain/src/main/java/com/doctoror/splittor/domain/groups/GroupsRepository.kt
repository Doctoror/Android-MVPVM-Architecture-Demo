package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.Flow

interface GroupsRepository {

    suspend fun insert(amount: String, contactNames: List<String>, title: String): Long

    fun observe(): Flow<List<Group>>

    fun observe(id: Long): Flow<Group>

    suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean)

    suspend fun delete(id: Long)
}
