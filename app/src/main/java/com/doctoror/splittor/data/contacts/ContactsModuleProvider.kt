package com.doctoror.splittor.data.contacts

import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import com.doctoror.splittor.domain.groups.GetContactDetailsUseCase
import org.koin.dsl.module

fun provideContactsModule() = module {

    factory {
        GetContactDetailsUseCase(contactDetailsRepository = get())
    }

    factory<ContactDetailsRepository> {
        ContactDetailsRepositoryImpl(contentResolver = get())
    }
}
