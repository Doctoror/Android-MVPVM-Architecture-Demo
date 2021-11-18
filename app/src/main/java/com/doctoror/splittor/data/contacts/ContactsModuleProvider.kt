package com.doctoror.splittor.data.contacts

import com.doctoror.splittor.domain.contacts.ContactDetailsResolver
import org.koin.dsl.module

fun provideContactsModule() = module {

    factory<ContactDetailsResolver> {
        ContactDetailsResolverImpl(contentResolver = get())
    }
}
