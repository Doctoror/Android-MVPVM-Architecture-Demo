package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.GroupMember
import org.junit.Assert.assertEquals
import org.junit.Test

class GroupMemberItemViewModelMapperTest {

    private val underTest = GroupMemberItemViewModelMapper()

    @Test
    fun transforms() {
        val amount = "55.44"
        val member = object : GroupMember {
            override val id = 489L
            override val name = "name"
            override val paid = true
        }

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
