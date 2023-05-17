package com.doctoror.splittor.presentation.addgroup

import android.os.Bundle
import com.doctoror.splittor.domain.contacts.ContactDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class AddGroupInputFieldsMonitorSaveRestoreInstanceStateTest {

    @Test
    fun savesAndRestoresInstanceState() {
        val state = Bundle()

        val amount = "amount"
        val contact = object : ContactDetails {
            override val id = 13L
            override val name = "name"
        }
        val title = "title"

        val toSave = AddGroupInputFieldsMonitor()
        toSave.contacts.add(contact)
        toSave.amount = amount
        toSave.title = title

        toSave.onSaveInstanceState(state)

        val toRestore = AddGroupInputFieldsMonitor()
        toRestore.onRestoreInstanceState(state)

        assertEquals(amount, toRestore.amount)
        assertEquals(title, toRestore.title)

        assertEquals(1, toRestore.contacts.size)
        val restoredContact = toRestore.contacts.first()
        assertEquals(contact.id, restoredContact.id)
        assertEquals(contact.name, restoredContact.name)
    }
}
