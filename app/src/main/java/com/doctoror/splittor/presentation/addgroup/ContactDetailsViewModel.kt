package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.ContactDetails

data class ContactDetailsViewModel(
    override val id: Long,
    override val name: String
) : ContactDetails, Comparable<ContactDetails> {

    override fun compareTo(other: ContactDetails) = id.compareTo(other.id)
}
