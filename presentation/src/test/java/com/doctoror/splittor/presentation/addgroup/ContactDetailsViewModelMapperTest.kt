package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.ContactDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactDetailsViewModelMapperTest {

    private val underTest = ContactDetailsViewModelMapper()

    @Test
    fun transforms() {
        val input = ContactDetails(
            id = 14L,
            name = "name"
        )

        val output = underTest.map(input)

        assertEquals(
            ContactDetailsViewModel(
                id = input.id,
                name = input.name
            ),
            output
        )
    }
}
