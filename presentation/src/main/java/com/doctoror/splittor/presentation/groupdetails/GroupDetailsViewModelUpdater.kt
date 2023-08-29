package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import java.math.BigDecimal
import java.math.RoundingMode

class GroupDetailsViewModelUpdater(
    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    private val groupMemberItemViewModelMapper: GroupMemberItemViewModelMapper
) {

    suspend fun update(viewModel: GroupDetailsViewModel, group: Group) {
        viewModel.amount.value = formatAmountWithCurrencyUseCase(BigDecimal(group.amount))
        viewModel.title.value = group.title

        val amountPerMember = formatAmountWithCurrencyUseCase(
            BigDecimal(group.amount).setScale(2, RoundingMode.HALF_UP) /
                    BigDecimal(group.members.size).setScale(2, RoundingMode.HALF_UP)
        )

        viewModel.members.emit(
            group.members.map {
                groupMemberItemViewModelMapper.map(amountPerMember, it)
            }
        )
    }
}
