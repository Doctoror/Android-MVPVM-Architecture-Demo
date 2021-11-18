package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.ContactDetails

class ContactDetailsViewModelMapper {

    fun map(contactDetails: ContactDetails) = ContactDetailsViewModel(
        id = contactDetails.id,
        name = contactDetails.name
    )
}
