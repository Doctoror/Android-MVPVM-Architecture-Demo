package com.doctoror.splittor.presentation.groupdetails

import android.text.SpannableString
import com.doctoror.splittor.domain.groups.GroupMember
import com.doctoror.splittor.platform.text.SpannableStringFactory
import com.doctoror.splittor.platform.text.StrikethroughTextTransformer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupMemberItemViewModelMapperTest {

    private val spannableStringFactory: SpannableStringFactory = mock()
    private val strikethroughTextTransformer: StrikethroughTextTransformer = mock()

    private val underTest = GroupMemberItemViewModelMapper(
        spannableStringFactory,
        strikethroughTextTransformer
    )

    @Test
    fun transformsWhenNotPaid() {
        testTransforms(paid = false)
    }

    @Test
    fun transformsWhenPaid() {
        testTransforms(paid = true)
    }

    private fun testTransforms(paid: Boolean) {
        val amount = "55.44"
        val member = object : GroupMember {
            override val id = 489L
            override val name = "name"
            override val paid = paid
        }

        val normalName: SpannableString = mock()
        whenever(spannableStringFactory.create(member.name)).thenReturn(normalName)

        val strikethroughName = "strikethroughName"
        whenever(strikethroughTextTransformer.strikethrough(member.name))
            .thenReturn(strikethroughName)

        val output = underTest.map(amount, member)

        assertEquals(
            output,
            GroupMemberItemViewModel(
                amount = amount,
                id = member.id,
                name = if (paid) {
                    strikethroughName
                } else {
                    normalName
                },
                paid = member.paid
            )
        )
    }
}
