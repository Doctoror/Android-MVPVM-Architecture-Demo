package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import java.util.Optional

class GetContactDetailsUseCase(private val contactDetailsRepository: ContactDetailsRepository) {

    suspend fun getForUri(uri: String): Optional<ContactDetails> =
        contactDetailsRepository.getForUri(uri)
}
