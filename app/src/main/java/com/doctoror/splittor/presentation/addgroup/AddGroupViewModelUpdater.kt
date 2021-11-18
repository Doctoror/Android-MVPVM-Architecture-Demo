package com.doctoror.splittor.presentation.addgroup

import androidx.annotation.StringRes
import com.doctoror.splittor.domain.contacts.ContactDetails

class AddGroupViewModelUpdater(
    private val contactDetailsViewModelMapper: ContactDetailsViewModelMapper,
    private val viewModel: AddGroupViewModel
) {

    fun addContact(contact: ContactDetails) {
        requireNotNull(viewModel.contacts.get(), { "Expecting members field always be set" })
            .add(contactDetailsViewModelMapper.map(contact))
        viewModel.contacts.notifyChange()
    }

    fun setErrorMessageId(@StringRes message: Int) {
        viewModel.errorMessage.set(message)
    }
}
