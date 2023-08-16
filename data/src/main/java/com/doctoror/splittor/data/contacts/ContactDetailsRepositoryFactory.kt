package com.doctoror.splittor.data.contacts

import android.content.ContentResolver
import com.doctoror.splittor.data.util.UriParser
import com.doctoror.splittor.domain.contacts.ContactDetailsRepository

class ContactDetailsRepositoryFactory {

    fun newInstance(contentResolver: ContentResolver): ContactDetailsRepository =
        ContactDetailsRepositoryImpl(
            contentResolver = contentResolver,
            uriParser = UriParser()
        )
}