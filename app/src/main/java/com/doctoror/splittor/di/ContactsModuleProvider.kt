package com.doctoror.splittor.di

import com.doctoror.splittor.data.contacts.ContactDetailsRepositoryFactory
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import org.koin.dsl.module

fun provideContactsModule() = module {

    factory { ContactDetailsRepositoryFactory().newInstance(contentResolver = get()) }

    factory { GetContactDetailsUseCase(contactDetailsRepository = get()) }
}
