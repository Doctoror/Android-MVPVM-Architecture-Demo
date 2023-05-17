package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupMember
import com.doctoror.splittor.platform.text.AmountFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class GroupDetailsViewModelUpdaterTest {

    private val amountFormatter: AmountFormatter = mock()
    private val groupMemberItemViewModelMapper: GroupMemberItemViewModelMapper = mock()
    private val viewModel = GroupDetailsViewModel()

    private val underTest = GroupDetailsViewModelUpdater(
        amountFormatter,
        groupMemberItemViewModelMapper,
        viewModel
    )

    @Test
    fun updatesAmount() {
        val formattedAmount = "$44.13"
        val group = makeBasicGroup()
        whenever(amountFormatter.format(group.amount)).thenReturn(formattedAmount)

        underTest.update(group)

        assertEquals(formattedAmount, viewModel.amount.get())
    }

    @Test
    fun updatesTitle() {
        val group = makeBasicGroup()

        underTest.update(group)

        assertEquals(group.title, viewModel.title.get())
    }

    @Test
    fun updatesMembersWithEqualAmounts() {
        val group = makeBasicGroup()
        val mappedMember1: GroupMemberItemViewModel = mock()
        val mappedMember2: GroupMemberItemViewModel = mock()

        val expectedAmount = "22.06"
        val formattedAmount = "$22.06"
        whenever(amountFormatter.format(BigDecimal(expectedAmount))).thenReturn(formattedAmount)

        whenever(groupMemberItemViewModelMapper.map(formattedAmount, group.members[0]))
            .thenReturn(mappedMember1)

        whenever(groupMemberItemViewModelMapper.map(formattedAmount, group.members[1]))
            .thenReturn(mappedMember2)

        underTest.update(group)

        assertEquals(listOf(mappedMember1, mappedMember2), viewModel.members.get())
    }

    private data class GroupMemberImpl(
        override val id: Long,
        override val name: String,
        override val paid: Boolean
    ) : GroupMember

    private data class GroupImpl(
        override val allMembersPaid: Boolean,
        override val amount: String,
        override val id: Long,
        override val insertedAt: Long,
        override val members: List<GroupMember>,
        override val title: String
    ) : Group

    private fun makeBasicGroup() = GroupImpl(
        allMembersPaid = false,
        amount = "44.13",
        id = 1L,
        insertedAt = 1684338081385,
        members = listOf(
            GroupMemberImpl(
                id = 1L,
                name = "name1",
                paid = false
            ),

            GroupMemberImpl(
                id = 2L,
                name = "name2",
                paid = true
            )
        ),
        title = "title"
    )
}
