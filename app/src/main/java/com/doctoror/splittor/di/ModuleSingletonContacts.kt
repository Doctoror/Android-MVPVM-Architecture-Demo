package com.doctoror.splittor.di

import android.content.ContentResolver
import com.doctoror.splittor.data.contacts.ContactDetailsRepositoryFactory
import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ModuleSingletonContacts {

    @Provides
    fun contactDetailsRepository(contentResolver: ContentResolver) =
        ContactDetailsRepositoryFactory().newInstance(contentResolver)

    @Provides
    fun getContactDetailsUseCase(contactDetailsRepository: ContactDetailsRepository) =
        GetContactDetailsUseCase(contactDetailsRepository)
}