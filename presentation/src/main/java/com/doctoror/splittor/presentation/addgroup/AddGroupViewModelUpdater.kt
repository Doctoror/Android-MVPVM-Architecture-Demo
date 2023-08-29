package com.doctoror.splittor.presentation.addgroup

import androidx.annotation.StringRes
import com.doctoror.splittor.domain.contacts.ContactDetails

class AddGroupViewModelUpdater(
    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper
) {

    private var sortedContacts: MutableSet<ContactDetailsViewModel>? = null

    fun addContact(viewModel: AddGroupViewModel, contact: ContactDetails) {
        if (sortedContacts == null) {
            sortedContacts = viewModel.contacts.value.toSortedSet()
        }
        sortedContacts!!.add(contactDetailsViewModelMapper.map(contact))

        val contactsList = sortedContacts!!.toList()
        viewModel.savedStateHandle[ADD_GROUP_VIEW_MODEL_KEY_CONTACTS] = contactsList
    }

    fun updateAmount(viewModel: AddGroupViewModel, amount: String) {
        viewModel.savedStateHandle[ADD_GROUP_VIEW_MODEL_KEY_AMOUNT] = amount
    }

    fun updateTitle(viewModel: AddGroupViewModel, title: String) {
        viewModel.savedStateHandle[ADD_GROUP_VIEW_MODEL_KEY_TITLE] = title
    }

    suspend fun setErrorMessageId(viewModel: AddGroupViewModel, @StringRes message: Int) {
        viewModel.errorMessage.emit(message)
    }
}
