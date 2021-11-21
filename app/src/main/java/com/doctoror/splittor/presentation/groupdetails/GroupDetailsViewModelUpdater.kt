package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.platform.text.AmountFormatter
import java.math.BigDecimal

class GroupDetailsViewModelUpdater(
    private val amountFormatter: AmountFormatter,
    private val groupMemberItemViewModelMapper: GroupMemberItemViewModelMapper,
    private val viewModel: GroupDetailsViewModel
) {

    fun update(group: Group) {
        viewModel.amount.set(amountFormatter.format(group.amount))
        viewModel.title.set(group.title)

        val amountPerMember = amountFormatter.format(
            BigDecimal(group.amount) / BigDecimal(group.members.size)
        )
        viewModel.members.set(
            group.members.map {
                groupMemberItemViewModelMapper.map(amountPerMember, it)
            }
        )
    }
}
