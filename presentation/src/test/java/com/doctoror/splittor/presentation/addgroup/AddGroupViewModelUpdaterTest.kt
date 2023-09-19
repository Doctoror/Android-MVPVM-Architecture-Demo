package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.presentation.R
import com.doctoror.splittor.presentation.base.executeBlockAndCollectFromFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional

class AddGroupViewModelUpdaterTest {

    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper = mock()
    private val viewModel = AddGroupViewModel()

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

    @Test
    fun setsErrorMessageId() {
        val messageId = R.string.amount_not_set
        val collected = executeBlockAndCollectFromFlow(
            viewModel
                .errorMessage
                .filter { it.isPresent }
        ) {
            runTest {
                underTest.setErrorMessageId(viewModel, Optional.of(messageId))
            }
        }

        assertEquals(messageId, collected.first().get())
    }
}
