package com.doctoror.splittor.data.contacts

import com.doctoror.splittor.domain.contacts.ContactDetails

internal data class ContactDetailsImpl(
    override val id: Long,
    override val name: String
) : ContactDetails
