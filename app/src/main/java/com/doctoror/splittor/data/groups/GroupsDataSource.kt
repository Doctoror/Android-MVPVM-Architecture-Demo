package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import kotlinx.coroutines.flow.Flow

interface GroupsDataSource {

    suspend fun insert(contacts: List<ContactDetails>, amount: String, title: String): Long

    fun observe(): Flow<List<Group>>

    fun observe(id: Long): Flow<Group>

    suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean)
}
