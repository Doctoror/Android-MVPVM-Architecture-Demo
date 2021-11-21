package com.doctoror.splittor.presentation.addgroup

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.google.android.material.internal.TextWatcherAdapter

class AddGroupInputFieldsMonitor : ViewModel() {

    var amount: Editable? = null

    var contacts = mutableSetOf<ContactDetails>()

    var title: Editable? = null

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
}
