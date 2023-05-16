package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.contacts.ContactDetails
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AddGroupViewModelUpdaterTest {

    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper = mock()
    private val viewModel = AddGroupViewModel()

    private val underTest = AddGroupViewModelUpdater(contactDetailsViewModelMapper, viewModel)

    @Test
    fun addsContact() {
        val details: ContactDetails = mock()
        val detailsViewModel: ContactDetailsViewModel = mock()
        whenever(contactDetailsViewModelMapper.map(details)).thenReturn(detailsViewModel)

        underTest.addContact(details)

        assertTrue(viewModel.contacts.get()!!.contains(detailsViewModel))
    }

    @Test
    fun setsErrorMessageId() {
        val messageId = R.string.amount_not_set

        underTest.setErrorMessageId(messageId)

        assertEquals(messageId, viewModel.errorMessage.get())
    }
}
