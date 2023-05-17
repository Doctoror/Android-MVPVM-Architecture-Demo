package com.doctoror.splittor.presentation.groupsoverview

import android.content.res.Resources
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupMember
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import com.doctoror.splittor.platform.text.StrikethroughTextTransformer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class GroupItemViewModelMapperTest {

    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase = mock()
    private val resources: Resources = mock()
    private val strikethroughTextTransformer: StrikethroughTextTransformer = mock()

    private val underTest = GroupItemViewModelMapper(
        formatAmountWithCurrencyUseCase,
        resources,
        strikethroughTextTransformer
    )

    @Test
    fun transformsWhenNotAllMembersPaid() {
        testTransforms(allMembersPaid = false)
    }

    @Test
    fun transformsWhenAllMembersPaid() {
        testTransforms(allMembersPaid = true)
    }

    private fun testTransforms(allMembersPaid: Boolean) {
        val group = object : Group {
            override val allMembersPaid = allMembersPaid
            override val amount = "19.58"
            override val id = 4L
            override val insertedAt = 1684234780123
            override val members = listOf(mock<GroupMember>())
            override val title = "title"
        }

        val formattedAmount = "formattedAmount"
        whenever(formatAmountWithCurrencyUseCase.format(BigDecimal(group.amount)))
            .thenReturn(formattedAmount)

        val formattedMemberCount = "formattedMemberCount"
        whenever(
            resources.getQuantityString(
                R.plurals.d_members,
                group.members.size,
                group.members.size
            )
        ).thenReturn(formattedMemberCount)

        val strikethroughTitle = "strikethroughTitle"
        whenever(strikethroughTextTransformer.strikethrough(group.title))
            .thenReturn(strikethroughTitle)

        val output = underTest.map(group)

        assertEquals(
            output,
            GroupItemViewModel(
                amount = formattedAmount,
                id = group.id,
                members = formattedMemberCount,
                title = if (allMembersPaid) {
                    strikethroughTitle
                } else {
                    group.title
                }
            )
        )
    }
}
