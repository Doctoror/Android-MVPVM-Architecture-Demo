package com.doctoror.splittor.domain.contacts

import java.util.Optional

class GetContactDetailsUseCase(private val contactDetailsRepository: ContactDetailsRepository) {

    suspend fun getForUri(uri: String): Optional<ContactDetails> =
        contactDetailsRepository.getForUri(uri)
}
