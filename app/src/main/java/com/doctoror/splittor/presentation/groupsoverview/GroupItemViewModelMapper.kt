package com.doctoror.splittor.presentation.groupsoverview

import android.content.res.Resources
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import java.math.BigDecimal

class GroupItemViewModelMapper(
    private val formatAmountWithCurrencyUseCase: FormatAmountWithCurrencyUseCase,
    private val resources: Resources
) {

    fun map(group: Group) = GroupItemViewModel(
        amount = formatAmountWithCurrencyUseCase(BigDecimal(group.amount)),
        allMembersPaid = group.allMembersPaid,
        id = group.id,
        members = resources.getQuantityString(
            R.plurals.d_members,
            group.members.size,
            group.members.size
        ),
        title = group.title
    )
}
