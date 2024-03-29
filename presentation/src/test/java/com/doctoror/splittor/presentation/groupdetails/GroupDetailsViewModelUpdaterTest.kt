package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupMember
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class GroupDetailsViewModelUpdaterTest {

    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase = mock()
    private val groupMemberItemViewModelMapper: GroupMemberItemViewModelMapper = mock()
    private val viewModel = GroupDetailsViewModel()

    private val underTest = GroupDetailsViewModelUpdater(
        formatAmountWithCurrencyUseCase,
        groupMemberItemViewModelMapper
    )

    @Test
    fun updatesAmount() = runTest {
        val formattedAmount = "$44.13"
        val group = makeBasicGroup().copy(members = emptyList())
        whenever(formatAmountWithCurrencyUseCase(BigDecimal(group.amount)))
            .thenReturn(formattedAmount)

        underTest.update(viewModel, group)

        assertEquals(formattedAmount, viewModel.amount.value)
    }

    @Test
    fun updatesTitle() = runTest {
        val group = makeBasicGroup().copy(members = emptyList())

        underTest.update(viewModel, group)

        assertEquals(group.title, viewModel.title.value)
    }

    @Test
    fun updatesMembersWithEqualAmounts() = runTest {
        val group = makeBasicGroup()
        val mappedMember1: GroupMemberItemViewModel = mock()
        val mappedMember2: GroupMemberItemViewModel = mock()

        val expectedAmount = "22.06"
        val formattedAmount = "$22.06"
        whenever(formatAmountWithCurrencyUseCase(BigDecimal(expectedAmount)))
            .thenReturn(formattedAmount)

        whenever(groupMemberItemViewModelMapper.map(formattedAmount, group.members[0]))
            .thenReturn(mappedMember1)

        whenever(groupMemberItemViewModelMapper.map(formattedAmount, group.members[1]))
            .thenReturn(mappedMember2)

        underTest.update(viewModel, group)

        assertEquals(listOf(mappedMember1, mappedMember2), viewModel.members.value)
    }

    private fun makeBasicGroup() = Group(
        allMembersPaid = false,
        amount = "44.13",
        id = 1L,
        insertedAt = 1684338081385,
        members = listOf(
            GroupMember(
                id = 1L,
                name = "name1",
                paid = false
            ),

            GroupMember(
                id = 2L,
                name = "name2",
                paid = true
            )
        ),
        title = "title"
    )
}
