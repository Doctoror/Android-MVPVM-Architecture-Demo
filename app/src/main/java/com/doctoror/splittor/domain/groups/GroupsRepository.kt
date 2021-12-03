package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.coroutines.flow.Flow

interface GroupsRepository {

    suspend fun insert(contacts: List<ContactDetails>, amount: String, title: String): Long

    fun observe(): Flow<List<Group>>

    fun observe(id: Long): Flow<Group>

    suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean)
}
