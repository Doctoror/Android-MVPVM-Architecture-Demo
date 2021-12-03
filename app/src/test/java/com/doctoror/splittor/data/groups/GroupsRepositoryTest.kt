package com.doctoror.splittor.data.groups

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupsRepositoryTest {

    private val groupsDataSource: GroupsDataSource = mock()

    private val underTest = GroupsRepositoryImpl(groupsDataSource)

    @Test
    fun observeEmitsDataFromDataSource() {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDataSource.observe()).thenReturn(flowOf(data))

        runBlocking { assertEquals(data, underTest.observe().single()) }
    }
}
