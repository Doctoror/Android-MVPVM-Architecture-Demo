package com.doctoror.splittor.presentation.groupsoverview

import android.content.res.Resources
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.platform.text.AmountFormatter
import com.doctoror.splittor.platform.text.StrikethroughTextTransformer

class GroupItemViewModelMapper(
    private val amountFormatter: AmountFormatter,
    private val resources: Resources,
    private val strikethroughTextTransformer: StrikethroughTextTransformer
) {

    fun map(group: Group) = GroupItemViewModel(
        amount = amountFormatter.format(group.amount),
        id = group.id,
        members = resources.getQuantityString(
            R.plurals.d_members,
            group.members.size,
            group.members.size
        ),
        title = formatTitle(group.title, group.allMembersPaid)
    )

    private fun formatTitle(name: CharSequence, allMembersPaid: Boolean): CharSequence =
        if (allMembersPaid) {
            strikethroughTextTransformer.strikethrough(name)
        } else {
            name
        }
}
