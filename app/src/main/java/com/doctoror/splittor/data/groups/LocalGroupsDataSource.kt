package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class LocalGroupsDataSource(private val groupsDao: GroupsDao) : GroupsDataSource {

    override fun insert(contacts: List<ContactDetails>, sum: String, title: String): Completable =
        groupsDao
            .insertGroup(
                GroupEntity(
                    groupId = 0,
                    groupSum = sum,
                    groupTitle = title
                )
            )
            .flatMapCompletable { groupId ->
                groupsDao.insertGroupMembers(
                    contacts
                        .map {
                            GroupMemberEntity(
                                groupMemberContactId = it.id,
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
}
