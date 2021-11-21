package com.doctoror.splittor.presentation.groups

import android.content.res.Resources
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.presentation.text.AmountFormatter

class GroupItemViewModelMapper(
    private val amountFormatter: AmountFormatter,
    private val resources: Resources
) {

    fun map(group: Group) = GroupItemViewModel(
        amount = amountFormatter.format(group.amount),
        id = group.id,
        members = resources.getQuantityString(
            R.plurals.d_members,
            group.members.size,
            group.members.size
        ),
        title = group.title
    )
}
