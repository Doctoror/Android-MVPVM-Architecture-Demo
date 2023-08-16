package com.doctoror.splittor.presentation.addgroup

import androidx.annotation.StringRes
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import com.doctoror.splittor.domain.contacts.ContactDetails

class AddGroupViewModelUpdater(
    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper,
    private val viewModel: AddGroupViewModel
) {

    private val sortedContacts: MutableSet<ContactDetailsViewModel> =
        viewModel.contacts.toSortedSet()

    fun addContact(contact: ContactDetails) {
        sortedContacts.add(contactDetailsViewModelMapper.map(contact))
        withMutableSnapshot {
            viewModel.contacts = sortedContacts.toList()
        }
    }

    fun updateAmount(amount: String) {
        withMutableSnapshot {
            viewModel.amount = amount
        }
    }

    fun updateTitle(title: String) {
        withMutableSnapshot {
            viewModel.title = title
        }
    }

    suspend fun setErrorMessageId(@StringRes message: Int) {
        viewModel.errorMessage.emit(message)
    }
}
