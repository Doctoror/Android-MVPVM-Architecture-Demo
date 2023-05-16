package com.doctoror.splittor.presentation.addgroup

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import com.doctoror.splittor.domain.contacts.ContactDetails
import kotlinx.parcelize.Parcelize
import java.util.*

private const val STATE_KEY_AMOUNT = "amount"
private const val STATE_KEY_CONTACTS = "contacts"
private const val STATE_KEY_TITLE = "title"

class AddGroupInputFieldsMonitor : ViewModel() {

    var amount: CharSequence? = null

    var contacts = mutableSetOf<ContactDetails>()

    var title: CharSequence? = null

    val amountTextWatcher: TextWatcher = object : TextWatcherAdapter() {

        override fun afterTextChanged(s: Editable) {
            amount = s
        }
    }

    val titleTextWatcher: TextWatcher = object : TextWatcherAdapter() {

        override fun afterTextChanged(s: Editable) {
            title = s
        }
    }

    fun addContact(contact: ContactDetails) {
        contacts.add(contact)
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            @Suppress("UNCHECKED_CAST")
            (savedInstanceState.getParcelable(STATE_KEY_CONTACTS, ArrayList::class.java)
                    as? ArrayList<ParcelableContactDetails>?)
                    ?.let(contacts::addAll)
        } else {
            @Suppress("DEPRECATION")
            savedInstanceState.getParcelableArrayList<ParcelableContactDetails>(STATE_KEY_CONTACTS)
                ?.let(contacts::addAll)
        }
        title = savedInstanceState.getCharSequence(STATE_KEY_TITLE)
    }

    @Parcelize
    private data class ParcelableContactDetails(
        override val id: Long,
        override val name: String
    ) : ContactDetails, Parcelable

    private open class TextWatcherAdapter : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }
}
