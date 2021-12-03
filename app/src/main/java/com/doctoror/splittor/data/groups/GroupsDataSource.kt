package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface GroupsDataSource {

    fun insert(contacts: List<ContactDetails>, amount: String, title: String): Single<Long>

    fun observe(): Flow<List<Group>>

    fun observe(id: Long): Observable<Group>

    fun updateMemberPaidStatus(memberId: Long, paid: Boolean): Completable
}
