package com.doctoror.splittor.domain.contacts

import android.net.Uri
import java.util.*

interface ContactDetailsResolver {

    fun resolve(uri: Uri): Optional<ContactDetails>
}
