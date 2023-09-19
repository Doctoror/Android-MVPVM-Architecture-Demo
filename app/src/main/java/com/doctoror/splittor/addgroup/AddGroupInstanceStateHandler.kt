package com.doctoror.splittor.addgroup

import android.os.Bundle
import androidx.core.os.BundleCompat
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModel
import com.doctoror.splittor.presentation.addgroup.ContactDetailsViewModel

private const val ADD_GROUP_VIEW_MODEL_KEY_AMOUNT = "amount"
private const val ADD_GROUP_VIEW_MODEL_KEY_CONTACTS = "contacts"
private const val ADD_GROUP_VIEW_MODEL_KEY_TITLE = "title"

class AddGroupInstanceStateHandler {

    fun onSaveInstanceState(viewModel: AddGroupViewModel, outState: Bundle) {
        with(outState) {
            putString(ADD_GROUP_VIEW_MODEL_KEY_AMOUNT, viewModel.amount.value)
            putString(ADD_GROUP_VIEW_MODEL_KEY_TITLE, viewModel.title.value)
            putParcelableArrayList(ADD_GROUP_VIEW_MODEL_KEY_CONTACTS, viewModel.contacts.value)
        }
    }

    fun onRestoreInstanceState(viewModel: AddGroupViewModel, savedInstanceState: Bundle) {
        viewModel.amount.value = savedInstanceState.getString(
            ADD_GROUP_VIEW_MODEL_KEY_AMOUNT,
            viewModel.amount.value
        )!!

        viewModel.title.value = savedInstanceState.getString(
            ADD_GROUP_VIEW_MODEL_KEY_TITLE,
            viewModel.title.value
        )!!

        viewModel.contacts.value = BundleCompat.getParcelableArrayList(
            savedInstanceState,
            ADD_GROUP_VIEW_MODEL_KEY_CONTACTS,
            ContactDetailsViewModel::class.java
        )!!
    }
}
