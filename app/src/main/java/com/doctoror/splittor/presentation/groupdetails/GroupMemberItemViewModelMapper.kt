package com.doctoror.splittor.presentation.groupdetails

import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import com.doctoror.splittor.domain.groups.GroupMember

class GroupMemberItemViewModelMapper {

    fun map(amountPerMember: CharSequence, groupMember: GroupMember) = GroupMemberItemViewModel(
        amount = amountPerMember,
        name = formatName(groupMember.title, groupMember.paid),
        paid = groupMember.paid
    )

    private fun formatName(name: CharSequence, paid: Boolean) = if (paid) {
        SpannableString(name).apply {
            setSpan(StrikethroughSpan(), 0, name.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
    } else {
        name
    }
}
