package com.doctoror.splittor.addgroup

import android.os.Bundle
import android.os.Parcel
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModel
import com.doctoror.splittor.presentation.addgroup.ContactDetailsViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class AddGroupInstanceStateHandlerTest {

    private val underTest = AddGroupInstanceStateHandler()

    @Test
    fun restoresSavedState() {
        val savedAmount = "13,49$"
        val savedTitle = "Dinner"
        val savedContacts = arrayListOf(
            ContactDetailsViewModel(
                id = 1L,
                name = "Alice"
            ),
            ContactDetailsViewModel(
                id = 2L,
                name = "Bob"
            )
        )

        val savedInstanceState = Bundle()
        underTest.onSaveInstanceState(
            AddGroupViewModel().apply {
                amount.value = savedAmount
                contacts.value = savedContacts
                title.value = savedTitle
            },
            savedInstanceState
        )

        val parcel = Parcel.obtain()
        parcel.writeBundle(savedInstanceState)
        parcel.setDataPosition(0)

        val restoreInViewModel = AddGroupViewModel()
        val savedInstanceStateFromParcel = parcel
            .readBundle(ContactDetailsViewModel::class.java.classLoader)!!
        underTest.onRestoreInstanceState(restoreInViewModel, savedInstanceStateFromParcel)

        assertEquals(savedAmount, restoreInViewModel.amount.value)
        assertEquals(savedContacts, restoreInViewModel.contacts.value)
        assertEquals(savedTitle, restoreInViewModel.title.value)
    }
}
