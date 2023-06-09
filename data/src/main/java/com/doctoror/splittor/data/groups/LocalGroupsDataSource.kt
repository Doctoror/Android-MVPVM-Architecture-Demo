package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalGroupsDataSource(
    private val currentTimeProvider: () -> Long,
    private val groupsDao: GroupsDao
) : GroupsDataSource {

    override suspend fun insert(
        amount: String,
        contactNames: List<String>,
        title: String
    ): Long {
        val groupId = groupsDao.insertGroup(
            GroupEntity(
                groupId = 0,
                groupAmount = amount,
                groupTitle = title,
                insertedAt = currentTimeProvider()
            )
        )

        groupsDao.insertGroupMembers(
            contactNames
                .map {
                    GroupMemberEntity(
                        groupMemberId = 0,
                        groupMemberGroupId = groupId,
                        groupMemberPaid = false,
                        groupMemberName = it
                    )
                }
        )

        return groupId
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
