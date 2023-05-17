package com.doctoror.splittor.domain.contacts

import java.util.Optional

interface ContactDetailsRepository {

    suspend fun getForUri(uri: String): Optional<ContactDetails>
}
