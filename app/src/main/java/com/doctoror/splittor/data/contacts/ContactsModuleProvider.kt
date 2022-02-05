package com.doctoror.splittor.data.contacts

import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import org.koin.dsl.module

fun provideContactsModule() = module {

    factory<ContactDetailsRepository> {
        ContactDetailsRepositoryImpl(contentResolver = get())
    }
}
