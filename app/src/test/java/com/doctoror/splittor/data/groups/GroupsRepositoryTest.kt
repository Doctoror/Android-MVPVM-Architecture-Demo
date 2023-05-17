package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GroupsRepositoryTest {

    private val groupsDataSource: GroupsDataSource = mock()

    private val underTest = GroupsRepositoryImpl(groupsDataSource)

    @Test
    fun insertsIntoDataSource() = runTest {
        val contacts: List<ContactDetails> = listOf(mock())
        val amount = "34343.12"
        val title = "Some title"

        val insertedGroupId = 125L
        whenever(groupsDataSource.insert(contacts, amount, title)).thenReturn(insertedGroupId)

        val output = underTest.insert(contacts, amount, title)

        assertEquals(insertedGroupId, output)
    }

    @Test
    fun observeEmitsDataFromDataSource() = runTest {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDataSource.observe()).thenReturn(flowOf(data))

        assertEquals(data, underTest.observe().single())
    }

    @Test
    fun observeByIdEmitsDataFromDataSource() = runTest {
        val id = 3434L
        val data: GroupWithMembers = mock()
        whenever(groupsDataSource.observe(id)).thenReturn(flowOf(data))

        assertEquals(data, underTest.observe(id).single())
    }

    @Test
    fun updatesMemberPaidStatusInDataSource() = runTest {
        val memberId = 134L
        val paid = false

        underTest.updateMemberPaidStatus(memberId, paid)

        verify(groupsDataSource).updateMemberPaidStatus(memberId, paid)
    }
}
