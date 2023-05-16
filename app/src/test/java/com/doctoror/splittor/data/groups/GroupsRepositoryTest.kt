package com.doctoror.splittor.data.groups

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupsRepositoryTest {

    private val groupsDataSource: GroupsDataSource = mock()

    private val underTest = GroupsRepositoryImpl(groupsDataSource)

    @Test
    fun observeEmitsDataFromDataSource() = runTest {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDataSource.observe()).thenReturn(flowOf(data))

        assertEquals(data, underTest.observe().single())
    }
}
