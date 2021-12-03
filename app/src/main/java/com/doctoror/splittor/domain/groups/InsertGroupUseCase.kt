package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails

class InsertGroupUseCase(private val groupsRepository: GroupsRepository) {

    suspend fun insert(
        amount: String,
        contacts: List<ContactDetails>,
        title: String
    ): Long = groupsRepository.insert(
        amount = amount,
        contacts = contacts,
        title = title
    )
}
