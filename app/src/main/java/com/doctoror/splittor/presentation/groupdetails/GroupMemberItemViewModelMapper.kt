package com.doctoror.splittor.presentation.groupdetails

import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import com.doctoror.splittor.domain.groups.GroupMember

class GroupMemberItemViewModelMapper {

    fun map(amountPerMember: CharSequence, groupMember: GroupMember) = GroupMemberItemViewModel(
        amount = amountPerMember,
        id = groupMember.id,
        name = formatName(groupMember.title, groupMember.paid),
        paid = groupMember.paid
    )

    private fun formatName(name: CharSequence, paid: Boolean): CharSequence = SpannableString(name)
        .apply {
            if (paid) {
                setSpan(StrikethroughSpan(), 0, name.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }
}
