package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.GroupMember
import org.junit.Assert.assertEquals
import org.junit.Test

class GroupMemberItemViewModelMapperTest {

    private val underTest = GroupMemberItemViewModelMapper()

    @Test
    fun transforms() {
        val amount = "55.44"
        val member = GroupMember(
            id = 489L,
            name = "name",
            paid = true
        )

        val output = underTest.map(amount, member)

        assertEquals(
            output,
            GroupMemberItemViewModel(
                amount = amount,
                id = member.id,
                name = member.name,
                paid = member.paid
            )
        )
    }
}
