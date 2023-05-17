package com.doctoror.splittor.domain.groups

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
        val contactNames = listOf("Someone")
        val title = "title"
        val insertedGroupId = 112L
        whenever(groupsRepository.insert(amount, contactNames, title)).thenReturn(insertedGroupId)

        val output = underTest.insert(amount, contactNames, title)

        assertEquals(insertedGroupId, output)
    }
}
