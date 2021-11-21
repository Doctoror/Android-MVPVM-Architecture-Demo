package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class LocalGroupsDataSource(private val groupsDao: GroupsDao) : GroupsDataSource {

    override fun insert(
        contacts: List<ContactDetails>,
        amount: String,
        title: String
    ): Completable = groupsDao
        .insertGroup(
            GroupEntity(
                groupId = 0,
                groupAmount = amount,
                groupTitle = title,
                insertedAt = System.currentTimeMillis()
            )
        )
        .flatMapCompletable { groupId ->
            groupsDao.insertGroupMembers(
                contacts
                    .map {
                        GroupMemberEntity(
                            groupMemberId = 0,
                            groupMemberGroupId = groupId,
                            groupMemberPaid = false,
                            groupMemberTitle = it.name
                        )
                    }
            )
        }

    override fun observe(): Observable<List<Group>> = groupsDao
        .observeGroupsWithMembers()
        .map { it as List<Group> }

    override fun observe(id: Long): Observable<Group> = groupsDao
        .observeGroupWithMembers(id)
        .map { it as Group }

    override fun updateMemberPaidStatus(memberId: Long, paid: Boolean): Completable = groupsDao
        .updateMemberPaidStatus(memberId, paid)
}
