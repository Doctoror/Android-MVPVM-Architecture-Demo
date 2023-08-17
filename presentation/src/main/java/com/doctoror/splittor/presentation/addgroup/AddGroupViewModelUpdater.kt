package com.doctoror.splittor.presentation.addgroup

import androidx.annotation.StringRes
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import com.doctoror.splittor.domain.contacts.ContactDetails

class AddGroupViewModelUpdater(
    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper
) {

    private var sortedContacts: MutableSet<ContactDetailsViewModel>? = null

    fun addContact(viewModel: AddGroupViewModel, contact: ContactDetails) {
        if (sortedContacts == null) {
            sortedContacts = viewModel.contacts.toSortedSet()
        }
        sortedContacts!!.add(contactDetailsViewModelMapper.map(contact))
        withMutableSnapshot {
            viewModel.contacts = sortedContacts!!.toList()
        }
    }

    fun updateAmount(viewModel: AddGroupViewModel, amount: String) {
        withMutableSnapshot {
            viewModel.amount = amount
        }
    }

    fun updateTitle(viewModel: AddGroupViewModel, title: String) {
        withMutableSnapshot {
            viewModel.title = title
        }
    }

    suspend fun setErrorMessageId(viewModel: AddGroupViewModel, @StringRes message: Int) {
        viewModel.errorMessage.emit(message)
    }
}
