package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class GroupsRepositoryImpl(private val groupsDataSource: GroupsDataSource) : GroupsRepository {

    override fun insert(contacts: List<ContactDetails>, sum: String, title: String): Completable =
        groupsDataSource.insert(contacts, sum, title)

    override fun observe(): Observable<List<Group>> = groupsDataSource.observe()
}
