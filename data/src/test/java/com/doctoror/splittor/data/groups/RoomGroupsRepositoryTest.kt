package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RoomGroupsRepositoryTest {

    private val currentTimeProvider = { 1684318017123 }
    private val groupsDao: GroupsDao = mock()
    private val groupWithMembersMapper: GroupWithMembersMapper = mock()

    private val underTest = RoomGroupsRepository(
        currentTimeProvider,
        groupsDao,
        groupWithMembersMapper
    )

    @Test
    fun insertsInDao() = runTest {
        val name1 = "name1"
        val name2 = "name2"
        val amount = "133.55"
        val contactNames = listOf(name1, name2)
        val title = "Some title"

        val insertedGroupId = 12L
        whenever(
            groupsDao.insertGroup(
                GroupEntity(
                    groupId = 0,
                    groupAmount = amount,
                    groupTitle = title,
                    groupInsertedAt = currentTimeProvider()
                )
            )
        ).thenReturn(insertedGroupId)

        val output = underTest.insert(amount, contactNames, title)

        verify(groupsDao).insertGroupMembers(
            contactNames
                .map {
                    GroupMemberEntity(
                        groupMemberId = 0,
                        groupMemberGroupId = insertedGroupId,
                        groupMemberPaid = false,
                        groupMemberName = it
                    )
                }
        )

        assertEquals(insertedGroupId, output)
    }

    @Test
    fun observeGroupsWithMembersEmitsDataFromDao() = runTest {
        val group: GroupWithMembers = mock()
        val groupTransformed: Group = mock()
        val data = listOf(group)

        whenever(groupWithMembersMapper.transform(group)).thenReturn(groupTransformed)
        whenever(groupsDao.observeGroupsWithMembers()).thenReturn(flowOf(data))

        assertEquals(listOf(groupTransformed), underTest.observe().single())
    }

    @Test
    fun observeGroupWithMembersEmitsDataFromDao() = runTest {
        val id = 1L

        val data: GroupWithMembers = mock()
        val groupTransformed: Group = mock()

        whenever(groupWithMembersMapper.transform(data)).thenReturn(groupTransformed)
        whenever(groupsDao.observeGroupWithMembers(id)).thenReturn(flowOf(data))

        assertEquals(groupTransformed, underTest.observe(id).single())
    }

    @Test
    fun updatesMemberPaidStatusInDao() = runTest {
        val memberId = 199L
        val paid = true

        underTest.updateMemberPaidStatus(memberId, paid)

        verify(groupsDao).updateMemberPaidStatus(memberId, paid)
    }

    @Test
    fun deletesGroupInDao() = runTest {
        val id = 13L

        underTest.delete(id)

        verify(groupsDao).deleteGroup(id)
    }
}
