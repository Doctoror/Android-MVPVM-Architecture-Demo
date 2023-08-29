package com.doctoror.splittor.presentation.addgroup

import androidx.lifecycle.SavedStateHandle
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.presentation.R
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
    private val savedStateHandle = SavedStateHandle()
    private val viewModel = AddGroupViewModel(savedStateHandle)

    private val underTest = AddGroupViewModelUpdater(contactDetailsViewModelMapper)

    @Test
    fun addsContact() {
        val details: ContactDetails = mock()
        val detailsViewModel: ContactDetailsViewModel = mock()
        whenever(contactDetailsViewModelMapper.map(details)).thenReturn(detailsViewModel)

        underTest.addContact(viewModel, details)

        assertTrue(viewModel.contacts.value.contains(detailsViewModel))
    }

    @Test
    fun updatesAmount() {
        val amount = "13.49"

        underTest.updateAmount(viewModel, amount)

        assertEquals(amount, viewModel.amount.value)
    }

    @Test
    fun updatesTitle() {
        val title = "Title"

        underTest.updateTitle(viewModel, title)

        assertEquals(title, viewModel.title.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun setsErrorMessageId() = runTest {
        val messageId = R.string.amount_not_set

        underTest.setErrorMessageId(viewModel, messageId)

        var collectedMessageId = -1
        runTest(UnconfinedTestDispatcher()) {

            val collectJob = launch {
                viewModel.errorMessage.collect { collectedMessageId = it }
            }

            underTest.setErrorMessageId(viewModel, messageId)

            collectJob.cancel()
        }

        assertEquals(messageId, collectedMessageId)
    }
}
