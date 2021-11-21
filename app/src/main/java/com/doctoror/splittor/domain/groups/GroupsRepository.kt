package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface GroupsRepository {

    fun insert(contacts: List<ContactDetails>, amount: String, title: String): Completable

    fun observe(): Observable<List<Group>>
}
