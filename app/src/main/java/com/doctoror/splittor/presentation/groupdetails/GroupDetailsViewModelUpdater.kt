package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import java.math.BigDecimal
import java.math.RoundingMode

class GroupDetailsViewModelUpdater(
    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    private val groupMemberItemViewModelMapper: GroupMemberItemViewModelMapper,
    private val viewModel: GroupDetailsViewModel
) {

    fun update(group: Group) {
        viewModel.amount.set(formatAmountWithCurrencyUseCase.format(BigDecimal(group.amount)))
        viewModel.title.set(group.title)

        val amountPerMember = formatAmountWithCurrencyUseCase.format(
            BigDecimal(group.amount).setScale(2, RoundingMode.HALF_UP) /
                    BigDecimal(group.members.size).setScale(2, RoundingMode.HALF_UP)
        )
        viewModel.members.set(
            group.members.map {
                groupMemberItemViewModelMapper.map(amountPerMember, it)
            }
        )
    }
}
