package com.doctoror.splittor.presentation.addgroup

import android.os.Parcelable
import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactDetailsViewModel(
    override val id: Long,
    override val name: String
) : ContactDetails, Comparable<ContactDetails>, Parcelable {

    override fun compareTo(other: ContactDetails) = id.compareTo(other.id)
}
