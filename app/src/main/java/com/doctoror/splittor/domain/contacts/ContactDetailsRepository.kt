package com.doctoror.splittor.domain.contacts

import android.net.Uri
import java.util.Optional

interface ContactDetailsRepository {

    suspend fun getForUri(uri: Uri): Optional<ContactDetails>
}
