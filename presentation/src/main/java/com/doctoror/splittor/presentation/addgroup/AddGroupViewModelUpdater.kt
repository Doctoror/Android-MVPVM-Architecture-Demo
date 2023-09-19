package com.doctoror.splittor.presentation.addgroup

import androidx.annotation.StringRes
import com.doctoror.splittor.domain.contacts.ContactDetails
import java.util.Optional

class AddGroupViewModelUpdater(
    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper
) {

    private var sortedContacts: MutableSet<ContactDetailsViewModel>? = null

    fun addContact(viewModel: AddGroupViewModel, contact: ContactDetails) {
        if (sortedContacts == null) {
            sortedContacts = viewModel.contacts.value.toSortedSet()
        }
        sortedContacts!!.add(contactDetailsViewModelMapper.map(contact))

        viewModel.contacts.value = ArrayList(sortedContacts!!)
    }

    fun updateAmount(viewModel: AddGroupViewModel, amount: String) {
        viewModel.amount.value = amount
    }

    fun updateTitle(viewModel: AddGroupViewModel, title: String) {
        viewModel.title.value = title
    }

    suspend fun setErrorMessageId(viewModel: AddGroupViewModel, @StringRes message: Optional<Int>) {
        viewModel.errorMessage.emit(message)
    }
}
