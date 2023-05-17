package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class InsertGroupUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = InsertGroupUseCase(groupsRepository)

    @Test
    fun inserts() = runTest {
        val amount = "amount"
        val contacts = listOf<ContactDetails>()
        val title = "title"
        val insertedGroupId = 112L
        whenever(groupsRepository.insert(contacts, amount, title)).thenReturn(insertedGroupId)

        val output = underTest.insert(amount, contacts, title)

        assertEquals(insertedGroupId, output)
    }
}
