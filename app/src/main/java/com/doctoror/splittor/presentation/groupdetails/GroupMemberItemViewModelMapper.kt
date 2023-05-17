package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.GroupMember
import com.doctoror.splittor.platform.text.SpannableStringFactory
import com.doctoror.splittor.platform.text.StrikethroughTextTransformer

class GroupMemberItemViewModelMapper(
    private val spannableStringFactory: SpannableStringFactory,
    private val strikethroughTextTransformer: StrikethroughTextTransformer
) {

    fun map(amountPerMember: CharSequence, groupMember: GroupMember) = GroupMemberItemViewModel(
        amount = amountPerMember,
        id = groupMember.id,
        name = formatName(groupMember.name, groupMember.paid),
        paid = groupMember.paid
    )

    private fun formatName(name: CharSequence, paid: Boolean): CharSequence = if (paid) {
        strikethroughTextTransformer.strikethrough(name)
    } else {
        // This would normally not be a SpannableString, but TextView does not update when you
        // replace SpannableString with normal String with the same text representation as it does
        // not detect a change, i.e. it is not possible to clear spans by setting a non-spannable
        // string with the same text. But setting a SpannableString without spans clears previous
        // spans
        spannableStringFactory.create(name)
    }
}
