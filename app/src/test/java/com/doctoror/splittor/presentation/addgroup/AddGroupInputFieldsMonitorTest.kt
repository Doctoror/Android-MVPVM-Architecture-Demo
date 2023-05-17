package com.doctoror.splittor.presentation.addgroup

import android.text.Editable
import com.doctoror.splittor.domain.contacts.ContactDetails
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock

class AddGroupInputFieldsMonitorTest {

    private val underTest = AddGroupInputFieldsMonitor()

    @Test
    fun addsContact() {
        val contact: ContactDetails = mock()

        underTest.addContact(contact)

        assertTrue(underTest.contacts.contains(contact))
    }

    @Test
    fun amountTextWatcherUpdatesAmountAfterTextChanged() {
        val text: Editable = mock()

        underTest.amountTextWatcher.afterTextChanged(text)

        assertEquals(text, underTest.amount)
    }

    @Test
    fun titleTextWatcherUpdatesTitleAfterTextChanged() {
        val text: Editable = mock()

        underTest.titleTextWatcher.afterTextChanged(text)

        assertEquals(text, underTest.title)
    }
}
