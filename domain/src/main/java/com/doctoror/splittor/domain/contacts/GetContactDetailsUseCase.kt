package com.doctoror.splittor.domain.contacts

import java.util.Optional

class GetContactDetailsUseCase(private val contactDetailsRepository: ContactDetailsRepository) {

    suspend operator fun invoke(uri: String): Optional<ContactDetails> =
        contactDetailsRepository.getForUri(uri)
}
