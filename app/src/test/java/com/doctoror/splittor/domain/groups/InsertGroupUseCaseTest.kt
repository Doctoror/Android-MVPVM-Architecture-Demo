package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertGroupUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = InsertGroupUseCase(groupsRepository)

    @Test
    fun inserts() = runTest {
        val amount = "amount"
        val contacts = listOf<ContactDetails>()
        val title = "title"

        underTest.insert(amount, contacts, title)

        verify(groupsRepository).insert(contacts, amount, title)
    }
}
