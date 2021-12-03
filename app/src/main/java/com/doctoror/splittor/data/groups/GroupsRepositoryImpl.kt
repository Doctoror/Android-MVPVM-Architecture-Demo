package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class GroupsRepositoryImpl(private val groupsDataSource: GroupsDataSource) : GroupsRepository {

    override fun insert(
        contacts: List<ContactDetails>,
        amount: String,
        title: String
    ): Single<Long> = groupsDataSource.insert(contacts, amount, title)

    override fun observe(): Flow<List<Group>> = groupsDataSource.observe()

    override fun observe(id: Long): Observable<Group> = groupsDataSource.observe(id)

    override fun updateMemberPaidStatus(memberId: Long, paid: Boolean): Completable =
        groupsDataSource.updateMemberPaidStatus(memberId, paid)
}
