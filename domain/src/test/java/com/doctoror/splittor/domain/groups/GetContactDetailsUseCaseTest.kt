package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional

class GetContactDetailsUseCaseTest {

    private val contactDetailsRepository: ContactDetailsRepository = mock()

    private val underTest = GetContactDetailsUseCase(contactDetailsRepository)

    @Test
    fun getsForUri() = runTest {
        val uri = "content://com.android.contacts/contacts/lookup/0r2-2C462C/1"
        val output = Optional.of(mock<ContactDetails>())
        whenever(contactDetailsRepository.getForUri(uri)).thenReturn(output)

        assertEquals(output, underTest.getForUri(uri))
    }
}
