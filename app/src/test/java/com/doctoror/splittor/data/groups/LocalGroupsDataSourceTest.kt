package com.doctoror.splittor.data.groups

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LocalGroupsDataSourceTest {

    private val groupsDao: GroupsDao = mock()

    private val underTest = LocalGroupsDataSource(groupsDao)

    @Test
    fun observeGroupsWithMembersEmitsDataFromDao() {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDao.observeGroupsWithMembers()).thenReturn(flowOf(data))

        runBlocking { assertEquals(data, underTest.observe().single()) }
    }
}
