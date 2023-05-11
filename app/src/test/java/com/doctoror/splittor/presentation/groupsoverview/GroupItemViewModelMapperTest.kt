package com.doctoror.splittor.presentation.groupsoverview

import android.content.res.Resources
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupMember
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class GroupItemViewModelMapperTest {

    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase = mock()
    private val resources: Resources = mock()

    private val underTest = GroupItemViewModelMapper(
        formatAmountWithCurrencyUseCase,
        resources
    )

    @Test
    fun transforms() {
        val group = object : Group {
            override val allMembersPaid = false
            override val amount = "19.58"
            override val id = 4L
            override val insertedAt = 1684234780123
            override val members = listOf(mock<GroupMember>())
            override val title = "title"
        }

        val formattedAmount = "formattedAmount"
        whenever(formatAmountWithCurrencyUseCase(BigDecimal(group.amount)))
            .thenReturn(formattedAmount)

        val formattedMemberCount = "formattedMemberCount"
        whenever(
            resources.getQuantityString(
                R.plurals.d_members,
                group.members.size,
                group.members.size
            )
        ).thenReturn(formattedMemberCount)

        val output = underTest.map(group)

        assertEquals(
            output,
            GroupItemViewModel(
                amount = formattedAmount,
                allMembersPaid = group.allMembersPaid,
                id = group.id,
                members = formattedMemberCount,
                title = group.title
            )
        )
    }
}
