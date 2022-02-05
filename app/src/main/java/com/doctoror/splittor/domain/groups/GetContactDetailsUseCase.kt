package com.doctoror.splittor.domain.groups

import android.net.Uri
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import java.util.*

class GetContactDetailsUseCase(private val contactDetailsRepository: ContactDetailsRepository) {

    suspend fun getForUri(uri: Uri): Optional<ContactDetails> =
        contactDetailsRepository.getForUri(uri)
}
