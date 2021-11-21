package com.doctoror.splittor.presentation.addgroup

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import androidx.lifecycle.ViewModel
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.google.android.material.internal.TextWatcherAdapter
import kotlinx.parcelize.Parcelize
import java.util.*

private const val STATE_KEY_AMOUNT = "amount"
private const val STATE_KEY_CONTACTS = "contacts"
private const val STATE_KEY_TITLE = "title"

class AddGroupInputFieldsMonitor : ViewModel() {

    var amount: CharSequence? = null

    var contacts = mutableSetOf<ContactDetails>()

    var title: CharSequence? = null

    val amountTextWatcher = object : TextWatcherAdapter() {

        override fun afterTextChanged(s: Editable) {
            amount = s
        }
    }

    val titleTextWatcher = object : TextWatcherAdapter() {

        override fun afterTextChanged(s: Editable) {
            title = s
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putCharSequence(STATE_KEY_AMOUNT, amount?.toString())

        outState.putParcelableArrayList(
            STATE_KEY_CONTACTS,
            ArrayList<ParcelableContactDetails>(contacts.size).apply {
                addAll(
                    contacts.map { ParcelableContactDetails(it.id, it.name) }
                )
            }
        )

        outState.putCharSequence(STATE_KEY_TITLE, title?.toString())
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        amount = savedInstanceState.getCharSequence(STATE_KEY_AMOUNT)
        savedInstanceState.getParcelableArrayList<ParcelableContactDetails>(STATE_KEY_CONTACTS)
            ?.let(contacts::addAll)
        title = savedInstanceState.getCharSequence(STATE_KEY_TITLE)
    }

    @Parcelize
    private data class ParcelableContactDetails(
        override val id: Long,
        override val name: String
    ) : ContactDetails, Parcelable
}
