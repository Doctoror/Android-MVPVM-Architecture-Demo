package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalGroupsDataSource(private val groupsDao: GroupsDao) : GroupsDataSource {

    override fun insert(
        contacts: List<ContactDetails>,
        amount: String,
        title: String
    ): Single<Long> = groupsDao
        .insertGroup(
            GroupEntity(
                groupId = 0,
                groupAmount = amount,
                groupTitle = title,
                insertedAt = System.currentTimeMillis()
            )
        )
        .flatMap { groupId ->
            groupsDao
                .insertGroupMembers(
                    contacts
                        .map {
                            GroupMemberEntity(
                                groupMemberId = 0,
                                groupMemberGroupId = groupId,
                                groupMemberPaid = false,
                                groupMemberName = it.name
                            )
                        }
                )
                .toSingleDefault(groupId)
        }

    override fun observe(): Flow<List<Group>> = groupsDao
        .observeGroupsWithMembers()
        .map { groups ->
            groups.sortedWith(
                compareBy<Group> { it.allMembersPaid }.thenByDescending { it.insertedAt }
            )
        }

    override fun observe(id: Long): Flow<Group> = groupsDao.observeGroupWithMembers(id)

    override suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean) = groupsDao
        .updateMemberPaidStatus(memberId, paid)
}
