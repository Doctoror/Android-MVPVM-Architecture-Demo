package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import io.reactivex.rxjava3.core.Single

class InsertGroupUseCase(private val groupsRepository: GroupsRepository) {

    fun insert(
        amount: String,
        contacts: List<ContactDetails>,
        title: String
    ): Single<Long> = groupsRepository.insert(
        amount = amount,
        contacts = contacts,
        title = title
    )
}
