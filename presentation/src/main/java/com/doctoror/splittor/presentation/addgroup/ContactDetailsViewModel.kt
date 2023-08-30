package com.doctoror.splittor.presentation.addgroup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactDetailsViewModel(
    val id: Long,
    val name: String
) : Comparable<ContactDetailsViewModel>, Parcelable {

    override fun compareTo(other: ContactDetailsViewModel) = name.compareTo(other.name)
}
