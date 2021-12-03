package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupsRepository
import kotlinx.coroutines.flow.Flow

class GroupsRepositoryImpl(private val groupsDataSource: GroupsDataSource) : GroupsRepository {

    override suspend fun insert(
        contacts: List<ContactDetails>,
        amount: String,
        title: String
    ): Long = groupsDataSource.insert(contacts, amount, title)

    override fun observe(): Flow<List<Group>> = groupsDataSource.observe()

    override fun observe(id: Long): Flow<Group> = groupsDataSource.observe(id)

    override suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean) =
        groupsDataSource.updateMemberPaidStatus(memberId, paid)
}
