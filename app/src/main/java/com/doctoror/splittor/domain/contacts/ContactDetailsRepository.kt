package com.doctoror.splittor.domain.contacts

import android.net.Uri
import java.util.*

interface ContactDetailsRepository {

    suspend fun getForUri(uri: Uri): Optional<ContactDetails>
}
