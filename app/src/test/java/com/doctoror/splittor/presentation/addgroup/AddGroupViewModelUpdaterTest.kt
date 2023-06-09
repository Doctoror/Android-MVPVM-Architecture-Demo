package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AddGroupViewModelUpdaterTest {

    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper = mock()
    private val viewModel = AddGroupViewModel(mock())

    private val underTest = AddGroupViewModelUpdater(contactDetailsViewModelMapper, viewModel)

    @Test
    fun addsContact() {
        val details: ContactDetails = mock()
        val detailsViewModel: ContactDetailsViewModel = mock()
        whenever(contactDetailsViewModelMapper.map(details)).thenReturn(detailsViewModel)

        underTest.addContact(details)

        assertTrue(viewModel.contacts.contains(detailsViewModel))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun setsErrorMessageId() = runTest {
        val messageId = R.string.amount_not_set

        underTest.setErrorMessageId(messageId)

        var collectedMessageId = -1
        runTest(UnconfinedTestDispatcher()) {

            val collectJob = launch {
                viewModel.errorMessage.collect { collectedMessageId = it }
            }

            underTest.setErrorMessageId(messageId)

            collectJob.cancel()
        }

        assertEquals(messageId, collectedMessageId)
    }
}
