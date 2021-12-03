package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails

class ValidateAddGroupInputFieldsUseCase {

    fun validate(
        amount: CharSequence?,
        contacts: Collection<ContactDetails>?,
        title: CharSequence?
    ): ValidationResult = when {
        title.isNullOrBlank() -> ValidationResult.TITLE_MISSING
        amount.isNullOrBlank() -> ValidationResult.AMOUNT_MISSING
        contacts.isNullOrEmpty() -> ValidationResult.CONTACTS_MISSING
        else -> ValidationResult.VALID
    }

    enum class ValidationResult {
        VALID, AMOUNT_MISSING, CONTACTS_MISSING, TITLE_MISSING
    }
}
