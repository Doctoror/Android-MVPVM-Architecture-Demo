package com.doctoror.splittor.presentation.addgroup

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.google.android.material.internal.TextWatcherAdapter

class AddGroupInputFieldsMonitor : ViewModel() {

    var contacts = mutableSetOf<ContactDetails>()

    var sum: Editable? = null

    var title: Editable? = null

    val sumTextWatcher = object : TextWatcherAdapter() {

        override fun afterTextChanged(s: Editable) {
            sum = s
        }
    }

    val titleTextWatcher = object : TextWatcherAdapter() {

        override fun afterTextChanged(s: Editable) {
            title = s
        }
    }
}
