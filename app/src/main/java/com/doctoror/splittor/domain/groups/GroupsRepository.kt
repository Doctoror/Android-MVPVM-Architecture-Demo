package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface GroupsRepository {

    fun insert(contacts: List<ContactDetails>, amount: String, title: String): Single<Long>

    fun observe(): Flow<List<Group>>

    fun observe(id: Long): Flow<Group>

    suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean)
}
